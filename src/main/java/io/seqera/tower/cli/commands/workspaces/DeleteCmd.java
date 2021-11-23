/*
 * Copyright (c) 2021, Seqera Labs.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as
 * defined by the Mozilla Public License, v. 2.0.
 */

package io.seqera.tower.cli.commands.workspaces;

import io.seqera.tower.ApiException;
import io.seqera.tower.cli.commands.global.WorkspaceRequiredOptions;
import io.seqera.tower.cli.responses.Response;
import io.seqera.tower.cli.responses.workspaces.WorkspaceDeleted;
import io.seqera.tower.model.OrgAndWorkspaceDbDto;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import java.io.IOException;

@Command(
        name = "delete",
        description = "Delete an organization workspace."
)
public class DeleteCmd extends AbstractWorkspaceCmd {

    @Mixin
    public WorkspaceRequiredOptions workspace;

    @Override
    protected Response exec() throws ApiException, IOException {
        Long wspId = workspaceId(workspace.workspace);
        
        OrgAndWorkspaceDbDto ws = workspaceById(wspId);
        api().deleteWorkspace(ws.getOrgId(), ws.getWorkspaceId());
        return new WorkspaceDeleted(ws.getWorkspaceName(), ws.getOrgName());
    }
}
