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

package io.seqera.tower.cli.commands.computeenvs;

import io.seqera.tower.ApiException;
import io.seqera.tower.cli.commands.AbstractApiCmd;
import io.seqera.tower.cli.exceptions.ComputeEnvNotFoundException;
import io.seqera.tower.cli.exceptions.NoComputeEnvironmentException;
import io.seqera.tower.cli.exceptions.TowerException;
import io.seqera.tower.model.ComputeEnv;
import io.seqera.tower.model.ListComputeEnvsResponseEntry;
import picocli.CommandLine.Command;

import java.util.List;

@Command
public abstract class AbstractComputeEnvCmd extends AbstractApiCmd {

    protected AbstractComputeEnvCmd() {
    }
    protected ComputeEnv fetchComputeEnv(ComputeEnvRefOptions computeEnvRefOptions, Long wspId) throws ApiException {
        ComputeEnv computeEnv;

        if (computeEnvRefOptions.computeEnv.computeEnvId != null) {
            computeEnv = computeEnvById(wspId, computeEnvRefOptions.computeEnv.computeEnvId);
        } else {
            computeEnv = computeEnvByName(wspId, computeEnvRefOptions.computeEnv.computeEnvName);
        }

        return computeEnv;
    }

    protected ComputeEnv computeEnvByName(Long workspaceId, String name) throws ApiException {

        List<ListComputeEnvsResponseEntry> computeEnvs = api().listComputeEnvs(null, workspaceId).getComputeEnvs();
        ListComputeEnvsResponseEntry entry = computeEnvs
                .stream()
                .filter(ce -> name.equals(ce.getName()))
                .findFirst()
                .orElseThrow(() -> new ComputeEnvNotFoundException(name, workspaceId));

        return api().describeComputeEnv(entry.getId(), workspaceId).getComputeEnv();
    }

    private ComputeEnv computeEnvById(Long workspaceId, String id) throws ApiException {
        return api().describeComputeEnv(id, workspaceId).getComputeEnv();
    }
}


