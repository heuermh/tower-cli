package io.seqera.tower.cli.commands.computeenvs.create;

import io.seqera.tower.ApiException;
import io.seqera.tower.cli.commands.computeenvs.platforms.Platform;
import io.seqera.tower.cli.responses.Response;
import io.seqera.tower.cli.utils.FilesHelper;
import io.seqera.tower.model.ComputeConfig;
import io.seqera.tower.model.ComputeEnv;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Path;

import static io.seqera.tower.cli.utils.JsonHelper.parseJson;

@CommandLine.Command(
        name = "json",
        description = "Create new compute environment from a JSON config file"
)
public class CreateJsonCmd extends AbstractCreateCmd {

    @Option(names = "--config", description = "JSON configuration file", required = true)
    Path config;

    @Override
    protected Response exec() throws ApiException, IOException {
        ComputeConfig configObj = parseJson(FilesHelper.readString(config), ComputeConfig.class);
        ComputeEnv.PlatformEnum platform = ComputeEnv.PlatformEnum.fromValue(configObj.getDiscriminator());
        return createComputeEnv(platform, configObj);
    }

    @Override
    protected Platform getPlatform() {
        throw new UnsupportedOperationException("Unknown platform");
    }
}
