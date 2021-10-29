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

package io.seqera.tower.cli.commands.credentials;

import io.seqera.tower.ApiException;
import io.seqera.tower.cli.responses.CredentialsList;
import io.seqera.tower.cli.responses.Response;
import io.seqera.tower.model.ListCredentialsResponse;
import picocli.CommandLine.Command;

import java.io.IOException;

@Command(
        name = "list",
        description = "List all workspace credentials"
)
public class ListCmd extends AbstractCredentialsCmd {

    @Override
    protected Response exec() throws ApiException, IOException {
        ListCredentialsResponse response = api().listCredentials(workspaceId(), null);
        return new CredentialsList(workspaceRef(), response.getCredentials());
    }
}
