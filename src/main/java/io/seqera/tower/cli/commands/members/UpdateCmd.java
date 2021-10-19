package io.seqera.tower.cli.commands.members;

import java.io.IOException;

import io.seqera.tower.ApiException;
import io.seqera.tower.cli.responses.Response;
import io.seqera.tower.cli.responses.members.MembersUpdate;
import io.seqera.tower.model.MemberDbDto;
import io.seqera.tower.model.OrgAndWorkspaceDbDto;
import io.seqera.tower.model.OrgRole;
import io.seqera.tower.model.UpdateMemberRoleRequest;
import picocli.CommandLine;

@CommandLine.Command(
        name = "update",
        description = "Update the role of an organization member"
)
public class UpdateCmd extends AbstractMembersClass {


    @CommandLine.Option(names = {"-o", "--organization"}, description = "Organization's name identifier", required = true)
    public String organizationName;

    @CommandLine.Option(names = {"-u", "--user"}, description = "Username or email of user to update from organization's members")
    public String user;

    @CommandLine.Option(names = {"-r", "--role"}, description = "Member's organization role (OWNER, MEMBER or COLLABORATOR)", required = true)
    public OrgRole role;

    @Override
    protected Response exec() throws ApiException, IOException {
        OrgAndWorkspaceDbDto orgAndWorkspaceDbDto = findOrganizationByName(organizationName);

        MemberDbDto member =  findMemberByUser(orgAndWorkspaceDbDto.getOrgId(), user);

        UpdateMemberRoleRequest request = new UpdateMemberRoleRequest();
        request.setRole(role);

        api().updateOrganizationMemberRole(orgAndWorkspaceDbDto.getOrgId(), member.getMemberId(), request);


        return new MembersUpdate(user, organizationName, role.toString());
    }
}
