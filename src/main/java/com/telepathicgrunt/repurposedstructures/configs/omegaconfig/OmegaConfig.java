package com.telepathicgrunt.repurposedstructures.configs.omegaconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Comment;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Config;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * A part of Omega Config that RS needs for serverside configs without syncs.
 * Original repo of Omega Config by Draylar (MIT License at the time of this comment):
 * https://github.com/Draylar/omega-config
 */
public class OmegaConfig {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "omega-config";

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final List<Config> REGISTERED_CONFIGURATIONS = new ArrayList<>();

    public static <T extends Config> T register(Class<T> configClass) {
        try {
            // Attempt to instantiate a new instance of this class.
            T config = configClass.getDeclaredConstructor().newInstance();

            // Exceptions will have been thrown at this point.
            // We want to provide access to the config as soon as it is created, so we:
            //    1. serialize to disk if the config does not already exist
            //    2. read from disk if it does exist
            if (!configExists(config)) {
                config.save();
                REGISTERED_CONFIGURATIONS.add(config);
            } else {
                try {
                    // Read from the disk config file to populate the correct values into our config object.
                    List<String> lines = Files.readAllLines(getConfigPath(config));
                    lines.removeIf(line -> line.trim().startsWith("//"));
                    StringBuilder res = new StringBuilder();
                    lines.forEach(res::append);
                    T object = GSON.fromJson(res.toString(), configClass);

                    // re-write the config to add new values
                    object.save();
                    REGISTERED_CONFIGURATIONS.add(object);
                    return object;
                } catch (Exception e) {
                    LOGGER.error(e);
                    LOGGER.info(String.format("Encountered an error while reading %s config, falling back to default values.", config.getName()));
                    LOGGER.info(String.format("If this problem persists, delete the config file %s and try again.", config.getName() + "." + config.getExtension()));
                }
            }

            return config;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
            throw new RuntimeException("No valid constructor found for: " + configClass.getName());
        }
    }

    public static <T extends Config> void writeConfig(Class<T> configClass, T instance) {
        // Write the config to disk with the default values.
        String json = GSON.toJson(instance);

        // Cursed time.
        List<String> lines = new ArrayList<>(Arrays.asList(json.split("\n")));
        Map<Integer, String> insertions = new TreeMap<>();
        Map<String, String> keyToComments = new HashMap<>();

        // populate key -> comments map
        for (Field field : configClass.getDeclaredFields()) {
            addFieldComments(field, keyToComments);
        }

        // get inner-class fields
        // TODO: recursively get inner classes?
        for (Class<?> innerClass : configClass.getDeclaredClasses()) {
            for (Field field : innerClass.getDeclaredFields()) {
                addFieldComments(field, keyToComments);
            }
        }

        // Find areas we should insert comments into...
        for (int i = 0; i < lines.size(); i++) {
            String at = lines.get(i);

            // Check if we should insert comment
            for (Map.Entry<String, String> entry : keyToComments.entrySet()) {
                String key = entry.getKey();
                String comment = entry.getValue();

                if (at.trim().startsWith(String.format("\"%s\"", key))) {
                    insertions.put(i + insertions.size(), String.format("%s//%s", getStartingWhitespace(at), comment));
                    break;
                }
            }
        }

        // insertions -> list
        for (Map.Entry<Integer, String> entry : insertions.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            lines.add(key, value);
        }

        // list -> string
        StringBuilder res = new StringBuilder();
        lines.forEach(str -> res.append(String.format("%s%n", str)));

        try {
            Path configPath = getConfigPath(instance);
            configPath.toFile().getParentFile().mkdirs();
            Files.write(configPath, res.toString().getBytes());
        } catch (IOException ioException) {
            LOGGER.error(ioException);
            LOGGER.info(String.format("Write error, using default values for config %s.", configClass));
        }
    }

    private static void addFieldComments(Field field, Map<String, String> keyToComments) {
        String fieldName = field.getName();
        Annotation[] annotations = field.getDeclaredAnnotations();

        // Find comment
        for (Annotation annotation : annotations) {
            if (annotation instanceof Comment) {
                keyToComments.put(fieldName, ((Comment) annotation).value());
                break;
            }
        }
    }

    /**
     * Returns a string with the left-side whitespace characters of the given input, up till the first non-whitespace character.
     *
     * <p>
     * "   hello" -> "   "
     * "p" -> ""
     * " p" -> " "
     *
     * @param input input to retrieve whitespaces from
     * @return starting whitespaces from the given input
     */
    private static String getStartingWhitespace(String input) {
        int index = -1;

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char at = chars[i];

            if (at != ' ') {
                index = i;
                break;
            }
        }

        if (index != -1) {
            return input.substring(0, index);
        } else {
            return "";
        }
    }

    public static Path getConfigPath(Config config) {
        return Paths.get(FMLPaths.CONFIGDIR.get().toString(), config.getDirectory(), String.format("%s.%s", config.getName(), config.getExtension()));
    }

    public static boolean configExists(Config config) {
        return Files.exists(getConfigPath(config));
    }

    public static List<Config> getRegisteredConfigurations() {
        return REGISTERED_CONFIGURATIONS;
    }
}
