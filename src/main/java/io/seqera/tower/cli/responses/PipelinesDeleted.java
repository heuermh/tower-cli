package io.seqera.tower.cli.responses;

public class PipelinesDeleted extends Response {

    private String id;
    private String workspaceRef;

    public PipelinesDeleted(String id, String workspaceRef) {
        this.id = id;
        this.workspaceRef = workspaceRef;
    }

    @Override
    public String toString() {
        return ansi(String.format("%n  @|yellow Pipeline '%s' deleted at %s workspace|@%n", id, workspaceRef));
    }
}
