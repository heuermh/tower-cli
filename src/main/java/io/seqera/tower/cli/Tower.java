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

/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package io.seqera.tower.cli;

import io.seqera.tower.cli.commands.AbstractCmd;
import io.seqera.tower.cli.commands.ActionsCmd;
import io.seqera.tower.cli.commands.CollaboratorsCmd;
import io.seqera.tower.cli.commands.ComputeEnvsCmd;
import io.seqera.tower.cli.commands.CredentialsCmd;
import io.seqera.tower.cli.commands.DatasetsCmd;
import io.seqera.tower.cli.commands.InfoCmd;
import io.seqera.tower.cli.commands.LaunchCmd;
import io.seqera.tower.cli.commands.MembersCmd;
import io.seqera.tower.cli.commands.OrganizationsCmd;
import io.seqera.tower.cli.commands.ParticipantsCmd;
import io.seqera.tower.cli.commands.PipelinesCmd;
import io.seqera.tower.cli.commands.RunsCmd;
import io.seqera.tower.cli.commands.TeamsCmd;
import io.seqera.tower.cli.commands.WorkspacesCmd;
import io.seqera.tower.cli.commands.enums.OutputType;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

import java.io.PrintWriter;

import static picocli.AutoComplete.GenerateCompletion;


@Command(
        name = "tw",
        description = "Nextflow Tower CLI.",
        subcommands = {
                ActionsCmd.class,
                CollaboratorsCmd.class,
                ComputeEnvsCmd.class,
                CredentialsCmd.class,
                DatasetsCmd.class,
                GenerateCompletion.class,
                InfoCmd.class,
                LaunchCmd.class,
                MembersCmd.class,
                OrganizationsCmd.class,
                ParticipantsCmd.class,
                PipelinesCmd.class,
                RunsCmd.class,
                TeamsCmd.class,
                WorkspacesCmd.class,
        }
)
public class Tower extends AbstractCmd {
    @Spec
    public CommandSpec spec;

    @Option(names = {"-t", "--access-token"}, description = "Tower personal access token (TOWER_ACCESS_TOKEN).", defaultValue = "${TOWER_ACCESS_TOKEN}", required = true)
    public String token;

    @Option(names = {"-u", "--url"}, description = "Tower server API endpoint URL (TOWER_API_ENDPOINT) [default: 'tower.nf'].", defaultValue = "${TOWER_API_ENDPOINT:-https://api.tower.nf}", required = true)
    public String url;

    @Option(names = {"-o", "--output"}, description = "Show output in defined format (only the 'json' option is available at the moment).")
    public OutputType output;

    @Option(names = {"-v", "--verbose"}, description = "Show HTTP request/response logs at stderr.")
    public boolean verbose;

    @Option(names = {"--insecure"}, description = "Explicitly allow to connect to a non-SSL secured Tower server (this is not recommended).")
    public boolean insecure;

    public Tower() {
    }

    public static void main(String[] args) {
        System.exit(buildCommandLine().execute(args));
    }

    protected static CommandLine buildCommandLine() {
        Tower app = new Tower();
        CommandLine cmd = new CommandLine(app);
        cmd.setUsageHelpLongOptionsMaxWidth(40);
        return cmd;
    }

    @Override
    public Integer call() {
        // if the command was invoked without subcommand, show the usage help
        spec.commandLine().usage(System.err);
        return -1;
    }

    public PrintWriter getErr() {
        return spec.commandLine().getErr();
    }

    public PrintWriter getOut() {
        return spec.commandLine().getOut();
    }
}
