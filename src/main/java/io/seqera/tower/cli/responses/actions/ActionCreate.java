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

package io.seqera.tower.cli.responses.actions;

import io.seqera.tower.cli.responses.Response;

public class ActionCreate extends Response {

    public final String actionName;
    public final String workspaceRef;
    public final String actionId;

    public ActionCreate(String actionName, String workspaceRef, String actionId) {
        this.actionName = actionName;
        this.workspaceRef = workspaceRef;
        this.actionId = actionId;
    }

    @Override
    public String toString() {
        return ansi(String.format("%n  @|yellow Pipeline action '%s' created at %s workspace with id '%s'|@%n", actionName, workspaceRef, actionId));
    }
}
