package com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api;


import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.OmegaConfig;

public interface Config {

    /**
     * Writes this configuration file instance to disk.
     * Useful for saving modified values during runtime.
     */
    default void save() {
        OmegaConfig.writeConfig((Class<Config>) getClass(), this);
    }

    /**
     * Returns the name of this config, which is used for the name of the config file saved to disk, and syncing.
     *
     * <p>
     * The name returned by this method should generally follow Identifier conventions, but this is not enforced:
     * <ul>
     *     <li>Lowercase
     *     <li>No special characters ($, %, ^, etc.)
     *     <li>No spaces
     *
     * @return the name of this config, which is used for the name of the config file saved to disk.
     */
    String getName();

    /**
     * Returns the modid associated with this configuration class.
     *
     * <p>
     * This functionality is used for libraries like ModMenu, which depend on
     * modids for configuration screen instances in their menu.
     * If you are intending for this config to have a ModMenu config
     * screen, this method should return the modid specified in your fabric.mod.json.
     * <p>
     * If this method is not overridden, null will be returned, which
     * means this config is not explicitly associated with any particular mod.
     *
     * @return the modid of the mod associated with this config, or null if none was specified
     */
    default String getModid() {
        return null;
    }

    /**
     * Returns the file extension of this config.
     *
     * <p>
     * The file extension is used while serializing this config to a local file.
     * The primary use-case of switching this would be supporting existing config files
     * when porting from other json5 config libraries.
     *
     * @return the file extension of this config
     */
    default String getExtension() {
        return "json";
    }

    /**
     * Returns the directory of this config, assuming the base directory is the instance config directory.
     *
     * <p>
     * By default, a config such as 'my_config' will appear at /config/my_config.json.
     * If this method specifies a directory, such as 'configurations',
     * the config file will appear at /config/configurations/my_config.json.
     * <p>
     * Nested directories can be specified by using a string such as 'configurations/client'.
     *
     * @return the directory of this config
     */
    default String getDirectory() {
        return "";
    }
}
