package com.telepathicgrunt.repurposedstructures.configs;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RSBiomeDimConfig {

    public static void createAndReadConfig(){
        String separator = File.separator;
        Path filePath = FMLPaths.CONFIGDIR.get().resolve("repurposed_structures-forge"+separator+"biome_dimension_allow_disallow_configs.toml");

        Map<String, String> map = Stream.of(new AbstractMap.SimpleEntry<>(
                "all", "the_bumblezone:the_bumblezone, " +
                        "twilightforest:twilightforest, " +
                        "undergarden:undergarden, " +
                        "the_midnight:the_midnight, " +
                        "theabyss:theabyssdim, " +
                        "theabyss:theabyssiceworld, " +
                        "theabyss:death, " +
                        "theabyss:the_end_of_time, " +
                        "theabyss:the_end_of_time_2, " +
                        "theabyss:dream, " +
                        "theabyss:dream_2, " +
                        "theabyss:dream_3, " +
                        "theabyss:radio, " +
                        "theabyss:theabyssdimgroundlands, " +
                        "theabyss:theabyssdimskylands")
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        try {
            JsonWriter writer = new Gson().newJsonWriter(new FileWriter(filePath.toFile()));

            //JSON5Parser parser = new JSON5Parser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
