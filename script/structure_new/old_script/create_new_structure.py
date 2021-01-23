from pathlib import Path
import collections.abc
import os
import sys
import random
import re

# Helper method from https://stackoverflow.com/a/49001704
def bend(w, s):
    s = s.split(" ") #creates list of all the words (any sequence between characters)
    lst = filter(None, s) # removes the repeated spaces from list
    new_lst = [""]
    i = 0
    for word in lst:
        line = new_lst[i] + " " + word #possible line
        if(new_lst[i] == ""): #first time is different
            line = "\""+word
        if(len(word) > w): #splits words that are too large
            while(len(word) > w):
                new_lst.append(word[:w])
                i += 1
                word = word[w:]
            i += 1
            new_lst.append(word)
        elif(len(line) > w):
           new_lst.append(word) #line length reached, start new line
           i += 1        
        else:
            new_lst[i] = line
    return "\"\n                + \"\\n".join(new_lst) + "\"" #insert new line characters

#--------------------------------------------------------------------------------------------

restart = True
while restart:
    
    structure_registry_name = input("\nregistry name of structure\n").strip()
    structure_variable_name = structure_registry_name.upper().strip()

    structure_size = input("\nsize of structure\n")
    start_pool = input("\nstart pool path\n").strip()
    biome_spacing = input("\nbiome spacing\n")
    structure_spacing = input("\navoid structure spacing\n")

    avoid_tags = ""
    avoid_tag = input("\nEnter structure tags to avoid. Type 'exit' to quit\n")
    while(avoid_tag != 'exit'): 
        avoid_tags = avoid_tags + ", STRUCTURE_TAGS." + avoid_tags.upper().strip()
        avoid_tag = input()
    avoid_tags = avoid_tags[2:]

    generation_step = input("\ngeneration step\n").upper().strip()

    config_category = input("\nconfig category\n")
    config_subcategory = input("\nconfig subcategory\n")

    config_modded_biome_entry = "add" + structure_registry_name.replace("_", " ").title().replace(" ", "") +"ToModdedBiomes"
    config_modded_biome_comment = input("\nconfig modded biome comment\n")

    config_spawnrate_entry = structure_registry_name[0].lower() + structure_registry_name.replace("_", " ").title().replace(" ", "")[1:] +"MaxChunkDistance"
    config_spawnrate_comment = input("\nconfig spawnrate comment\n")

    spacing_default_value = input("\nspacing default value\n")
    spacing_seed = str(random.getrandbits(31))
    adjusts_surface = input("\nadjusts surface (y/n)\n")

    innate_tags = ""
    innate_tag = input("\nEnter innate tags. Type 'exit' to quit\n")
    while(innate_tag != 'exit'): 
        innate_tags = innate_tags + ", STRUCTURE_TAGS." + innate_tag.upper().strip()
        innate_tag = input()
    innate_tags = innate_tags[2:]

    advancement_title = input("\nadvancement title\n")
    advancement_description = input("\nadvancement description\n")
    advancement_icon = input("\nadvancement icon\n")
    advancement_exp = str(input("\nadvancement exp\n"))
        


    #-------------------------------------------------------------------------------------------
    currentfile = open(os.path.join('code_output', structure_registry_name+'_code_output.txt'), "w")

    currentfile.write("Fabric code: \n\n")

    currentfile.write("\n")
    currentfile.write("\n    public static StructureFeature<DefaultFeatureConfig> "+structure_variable_name+" = new GenericJigsawStructure(new Identifier(RepurposedStructures.MODID, \""+start_pool+"\"), "+structure_size+", 0, "+biome_spacing+", "+structure_spacing+", "+("new HashSet<>()", "Stream.of("+avoid_tags+").collect(Collectors.toSet())")[bool(avoid_tags)]+");")
    currentfile.write("\n        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+").step(GenerationStep.Feature."+generation_step+").defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+", (int) (RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+" * 0.5f), "+spacing_seed+")).superflatFeature("+structure_variable_name+".configure(FeatureConfig.DEFAULT))"+("", ".adjustsSurface()")[adjusts_surface == 'y']+".register();")
    currentfile.write("\n")
    currentfile.write("\n    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> "+structure_variable_name+" = RSStructures."+structure_variable_name+".configure(FeatureConfig.DEFAULT);")
    currentfile.write("\n        Registry.register(registry, new Identifier(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+");")
    currentfile.write("\n")
    currentfile.write("\n        addTags(RSStructures."+structure_variable_name+", Stream.of("+innate_tags+").collect(Collectors.toSet()));")
    currentfile.write("\n")
    currentfile.write("\n        addToBiome(\""+structure_registry_name+"\",")
    currentfile.write("\n                (context) -> isBiomeAllowed(\""+config_subcategory+"\", context.getBiomeKey().getValue())")
    currentfile.write("\n                        && (RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+" != 1001")
    currentfile.write("\n                        && ()")
    currentfile.write("\n                        && (context.getBiomeKey().getValue().getNamespace().equals(\"minecraft\")")
    currentfile.write("\n                            || RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_modded_biome_entry+")),")
    currentfile.write("\n                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures."+structure_variable_name+"));")
    currentfile.write("\n")
    currentfile.write("\n        @ConfigEntry.Gui.Tooltip(count = 0)")
    currentfile.write("\n        @ConfigEntry.Gui.PrefixText")
    currentfile.write("\n        @Comment("+bend(50, config_modded_biome_comment)+")")
    currentfile.write("\n        public boolean "+config_modded_biome_entry+" = true;")
    currentfile.write("\n")
    currentfile.write("\n        @ConfigEntry.Gui.Tooltip(count = 0)")
    currentfile.write("\n        @ConfigEntry.Gui.PrefixText")
    currentfile.write("\n        @Comment("+bend(50, config_spawnrate_comment)+")")
    currentfile.write("\n        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)")
    currentfile.write("\n        public int "+config_spawnrate_entry+" = "+spacing_default_value+";")
    currentfile.write("\n")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+"\": \""+config_spawnrate_entry[0].upper()+re.sub('([A-Z])', r' \1', config_spawnrate_entry)[1:]+"\",")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+".@PrefixText\":\""+config_spawnrate_comment+"\",")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_modded_biome_entry+"\": \""+config_modded_biome_entry[0].upper()+re.sub('([A-Z])', r' \1', config_modded_biome_entry)[1:]+"\",")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_modded_biome_entry+".@PrefixText\":\""+config_modded_biome_comment+"\",")
    currentfile.write("\n")

    currentfile.write("\n----------------------------")

    currentfile.write("\nForge code: \n\n")

    currentfile.write("\n")
    currentfile.write("\n    public static final RegistryObject<Structure<NoFeatureConfig>> "+structure_variable_name+" = registerStructure(\""+structure_registry_name+"\", () -> (new GenericJigsawStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID, \""+start_pool+"\"), "+structure_size+", 0, "+biome_spacing+", "+structure_spacing+", "+("new HashSet<>()", "Stream.of("+avoid_tags+").collect(Collectors.toSet())")[bool(avoid_tags)]+")));")
    currentfile.write("\n        "+("registerStructure","registerLandscapeTransformingStructure")[adjusts_surface == 'y']+"(new ResourceLocation(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+".get(), GenerationStage.Decoration."+generation_step+", new StructureSeparationSettings(RepurposedStructures."+config_category+"."+config_spawnrate_entry+".get(), (int) (RepurposedStructures."+config_category+"."+config_spawnrate_entry+".get() * 0.5f), "+spacing_seed+"));")
    currentfile.write("\n")
    currentfile.write("\n    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> "+structure_variable_name+" = RSStructures."+structure_variable_name+".get().configure(IFeatureConfig.NO_FEATURE_CONFIG);")
    currentfile.write("\n        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+");")
    currentfile.write("\n")
    currentfile.write("\n        addTags(RSStructures."+structure_variable_name+".get(), Stream.of("+innate_tags+").collect(Collectors.toSet()));")
    currentfile.write("\n")
    currentfile.write("\n        if (RepurposedStructures.RSMainConfig."+config_spawnrate_entry+".get() != 1001) {")
    currentfile.write("\n            if (() &&")
    currentfile.write("\n                    (event.getName().getNamespace().equals(\"minecraft\") || RepurposedStructures."+config_category+"."+config_modded_biome_entry+".get())) {")
    currentfile.write("\n                event.getGeneration().getStructures().add(() -> RSConfiguredStructures."+structure_variable_name+");")
    currentfile.write("\n            }")
    currentfile.write("\n        }")
    currentfile.write("\n")
    currentfile.write("\n					"+config_spawnrate_entry+" = subscriber.subscribe(builder")
    currentfile.write("\n						.comment("+bend(50, config_spawnrate_comment)+")")
    currentfile.write("\n						.translation(\"repurposedstructures.config."+config_subcategory+"."+config_spawnrate_entry.lower()+"\")")
    currentfile.write("\n						.defineInRange(\""+config_spawnrate_entry+"\", "+spacing_default_value+", 1, 1001));")
    currentfile.write("\n")
    currentfile.write("\n					"+config_modded_biome_entry+" = subscriber.subscribe(builder")
    currentfile.write("\n						.comment("+bend(50, config_modded_biome_comment)+")")
    currentfile.write("\n						.translation(\"repurposedstructures.config."+config_subcategory+"."+config_modded_biome_entry.lower()+"\")")
    currentfile.write("\n						.define(\""+config_modded_biome_entry+"\", true));")
    currentfile.write("\n")
    currentfile.write("\n		public ConfigValueListener<Integer> "+config_spawnrate_entry+";")
    currentfile.write("\n		public ConfigValueListener<Boolean> "+config_modded_biome_entry+";")
    currentfile.write("\n")

    currentfile.write("\n----------------------------")

    currentfile.write("\nTranslation/Advancements code: \n\n")
    currentfile.write("\n")
    currentfile.write("\n  \"advancements.repurposed_structures."+structure_registry_name+".title\": \""+advancement_title+"\",")
    currentfile.write("\n  \"advancements.repurposed_structures."+structure_registry_name+".description\": \""+advancement_description+"\",")
    currentfile.write("\n")
    currentfile.close()


    currentfile = open(os.path.join('pre_translated', structure_registry_name+'.json'), "w")
    currentfile.write("\n{")
    currentfile.write("\n  \"parent\": \"repurposed_structures:"+config_subcategory+"\",")
    currentfile.write("\n  \"display\": {")
    currentfile.write("\n    \"icon\": {")
    currentfile.write("\n      \"item\": \""+advancement_icon+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"title\": {")
    currentfile.write("\n      \"text\": \""+advancement_title+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"description\": {")
    currentfile.write("\n      \"text\": \""+advancement_description+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"hidden\": true")
    currentfile.write("\n  },")
    currentfile.write("\n  \"rewards\": {")
    currentfile.write("\n    \"experience\": "+advancement_exp)
    currentfile.write("\n  },")
    currentfile.write("\n  \"criteria\": {")
    currentfile.write("\n    \"in_"+structure_registry_name+"\": {")
    currentfile.write("\n      \"trigger\": \"minecraft:location\",")
    currentfile.write("\n      \"conditions\": {")
    currentfile.write("\n        \"feature\": \"repurposed_structures:"+structure_registry_name+"\"")
    currentfile.write("\n      }")
    currentfile.write("\n    }")
    currentfile.write("\n  },")
    currentfile.write("\n  \"requirements\": [")
    currentfile.write("\n    [")
    currentfile.write("\n      \"in_"+structure_registry_name+"\"")
    currentfile.write("\n    ]")
    currentfile.write("\n  ]")
    currentfile.write("\n}")
    currentfile.close()


    currentfile = open(os.path.join('hidden', structure_registry_name+'.json'), "w")
    currentfile.write("\n{")
    currentfile.write("\n  \"parent\": \"repurposed_structures:"+config_subcategory+"\",")
    currentfile.write("\n  \"display\": {")
    currentfile.write("\n    \"icon\": {")
    currentfile.write("\n      \"item\": \""+advancement_icon+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"title\": {")
    currentfile.write("\n      \"text\": \""+advancement_title+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"description\": {")
    currentfile.write("\n      \"text\": \""+advancement_description+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"show_toast\": false")
    currentfile.write("\n    \"announce_to_chat\": false")
    currentfile.write("\n    \"hidden\": true")
    currentfile.write("\n  },")
    currentfile.write("\n  \"rewards\": {")
    currentfile.write("\n    \"experience\": "+advancement_exp)
    currentfile.write("\n  },")
    currentfile.write("\n  \"criteria\": {")
    currentfile.write("\n    \"in_"+structure_registry_name+"\": {")
    currentfile.write("\n      \"trigger\": \"minecraft:location\",")
    currentfile.write("\n      \"conditions\": {")
    currentfile.write("\n        \"feature\": \"repurposed_structures:"+structure_registry_name+"\"")
    currentfile.write("\n      }")
    currentfile.write("\n    }")
    currentfile.write("\n  },")
    currentfile.write("\n  \"requirements\": [")
    currentfile.write("\n    [")
    currentfile.write("\n      \"in_"+structure_registry_name+"\"")
    currentfile.write("\n    ]")
    currentfile.write("\n  ]")
    currentfile.write("\n}")
    currentfile.close()


    currentfile = open(os.path.join('disabled', structure_registry_name+'.json'), "w")
    currentfile.write("\n{")
    currentfile.write("\n  \"parent\": \"repurposed_structures:"+config_subcategory+"\",")
    currentfile.write("\n  \"display\": {")
    currentfile.write("\n    \"icon\": {")
    currentfile.write("\n      \"item\": \""+advancement_icon+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"title\": {")
    currentfile.write("\n      \"text\": \""+advancement_title+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"description\": {")
    currentfile.write("\n      \"text\": \""+advancement_description+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"hidden\": true")
    currentfile.write("\n  },")
    currentfile.write("\n  \"rewards\": {")
    currentfile.write("\n    \"experience\": "+advancement_exp)
    currentfile.write("\n  },")
    currentfile.write("\n  \"criteria\": {")
    currentfile.write("\n    \"in_"+structure_registry_name+"\": {")
    currentfile.write("\n      \"trigger\": \"minecraft:impossible\"")
    currentfile.write("\n    }")
    currentfile.write("\n  },")
    currentfile.write("\n  \"requirements\": [")
    currentfile.write("\n    [")
    currentfile.write("\n      \"in_"+structure_registry_name+"\"")
    currentfile.write("\n    ]")
    currentfile.write("\n  ]")
    currentfile.write("\n}")
    currentfile.close()


    currentfile = open(os.path.join('translation_key', structure_registry_name+'.json'), "w")
    currentfile.write("\n{")
    currentfile.write("\n  \"parent\": \"repurposed_structures:"+config_subcategory+"\",")
    currentfile.write("\n  \"display\": {")
    currentfile.write("\n    \"icon\": {")
    currentfile.write("\n      \"item\": \""+advancement_icon+"\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"title\": {")
    currentfile.write("\n      \"translate\": \"advancements.repurposed_structures."+structure_registry_name+".title\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"description\": {")
    currentfile.write("\n      \"translate\": \"advancements.repurposed_structures."+structure_registry_name+".description\"")
    currentfile.write("\n    },")
    currentfile.write("\n    \"hidden\": true")
    currentfile.write("\n  },")
    currentfile.write("\n  \"rewards\": {")
    currentfile.write("\n    \"experience\": "+advancement_exp)
    currentfile.write("\n  },")
    currentfile.write("\n  \"criteria\": {")
    currentfile.write("\n    \"in_"+structure_registry_name+"\": {")
    currentfile.write("\n      \"trigger\": \"minecraft:location\",")
    currentfile.write("\n      \"conditions\": {")
    currentfile.write("\n        \"feature\": \"repurposed_structures:"+structure_registry_name+"\"")
    currentfile.write("\n      }")
    currentfile.write("\n    }")
    currentfile.write("\n  },")
    currentfile.write("\n  \"requirements\": [")
    currentfile.write("\n    [")
    currentfile.write("\n      \"in_"+structure_registry_name+"\"")
    currentfile.write("\n    ]")
    currentfile.write("\n  ]")
    currentfile.write("\n}")
    currentfile.write("\n")
    currentfile.close()




    currentfile = open(os.path.join('fabric', 'structure_init.txt'), "a")
    currentfile.write("\n    public static StructureFeature<DefaultFeatureConfig> "+structure_variable_name+" = new GenericJigsawStructure(new Identifier(RepurposedStructures.MODID, \""+start_pool+"\"), "+structure_size+", 0, "+biome_spacing+", "+structure_spacing+", "+("new HashSet<>()", "Stream.of("+avoid_tags+").collect(Collectors.toSet())")[bool(avoid_tags)]+");")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'structure_registration.txt'), "a")
    currentfile.write("\n        FabricStructureBuilder.create(new Identifier(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+").step(GenerationStep.Feature."+generation_step+").defaultConfig(new StructureConfig(RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+", (int) (RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+" * 0.5f), "+spacing_seed+")).superflatFeature("+structure_variable_name+".configure(FeatureConfig.DEFAULT))"+("", ".adjustsSurface()")[adjusts_surface == 'y']+".register();")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'configured_structure_int.txt'), "a")
    currentfile.write("\n    public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> "+structure_variable_name+" = RSStructures."+structure_variable_name+".configure(FeatureConfig.DEFAULT);")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'configured_structure_registration.txt'), "a")
    currentfile.write("\n        Registry.register(registry, new Identifier(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+");")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'tags.txt'), "a")
    currentfile.write("\n        addTags(RSStructures."+structure_variable_name+", Stream.of("+innate_tags+").collect(Collectors.toSet()));")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'spawning.txt'), "a")
    currentfile.write("\n\n        addToBiome(\""+structure_registry_name+"\",")
    currentfile.write("\n                (context) -> isBiomeAllowed(\""+config_subcategory+"\", context.getBiomeKey().getValue())")
    currentfile.write("\n                        && (RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+" != 1001")
    currentfile.write("\n                        && ()")
    currentfile.write("\n                        && (context.getBiomeKey().getValue().getNamespace().equals(\"minecraft\")")
    currentfile.write("\n                            || RepurposedStructures.RSAllConfig."+config_category+"."+config_subcategory+"."+config_modded_biome_entry+")),")
    currentfile.write("\n                context -> context.getGenerationSettings().addBuiltInStructure(RSConfiguredStructures."+structure_variable_name+"));")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'config.txt'), "a")
    currentfile.write("\n\n        @ConfigEntry.Gui.Tooltip(count = 0)")
    currentfile.write("\n        @ConfigEntry.Gui.PrefixText")
    currentfile.write("\n        @Comment("+bend(50, config_modded_biome_comment)+")")
    currentfile.write("\n        public boolean "+config_modded_biome_entry+" = true;")
    currentfile.write("\n")
    currentfile.write("\n        @ConfigEntry.Gui.Tooltip(count = 0)")
    currentfile.write("\n        @ConfigEntry.Gui.PrefixText")
    currentfile.write("\n        @Comment("+bend(50, config_spawnrate_comment)+")")
    currentfile.write("\n        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)")
    currentfile.write("\n        public int "+config_spawnrate_entry+" = "+spacing_default_value+";")
    currentfile.write("\n")
    currentfile.close()

    currentfile = open(os.path.join('fabric', 'en_us_translations.txt'), "a")
    currentfile.write("\n\n  \"advancements.repurposed_structures."+structure_registry_name+".title\": \""+advancement_title+"\",")
    currentfile.write("\n  \"advancements.repurposed_structures."+structure_registry_name+".description\": \""+advancement_description+"\",")
    currentfile.write("\n")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+"\": \""+config_spawnrate_entry[0].upper()+re.sub('([A-Z])', r' \1', config_spawnrate_entry)[1:]+"\",")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_spawnrate_entry+".@PrefixText\":\""+config_spawnrate_comment+"\",")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_modded_biome_entry+"\": \""+config_modded_biome_entry[0].upper()+re.sub('([A-Z])', r' \1', config_modded_biome_entry)[1:]+"\",")
    currentfile.write("\n  \"text.autoconfig.repurposed_structures-main.option."+config_category+"."+config_subcategory+"."+config_modded_biome_entry+".@PrefixText\":\""+config_modded_biome_comment+"\",")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'structure_init.txt'), "a")
    currentfile.write("\n    public static final RegistryObject<Structure<NoFeatureConfig>> "+structure_variable_name+" = registerStructure(\""+structure_registry_name+"\", () -> (new GenericJigsawStructure(NoFeatureConfig.CODEC, new ResourceLocation(RepurposedStructures.MODID, \""+start_pool+"\"), "+structure_size+", 0, "+biome_spacing+", "+structure_spacing+", "+("new HashSet<>()", "Stream.of("+avoid_tags+").collect(Collectors.toSet())")[bool(avoid_tags)]+")));")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'structure_registration.txt'), "a")
    currentfile.write("\n        "+("registerStructure","registerLandscapeTransformingStructure")[adjusts_surface == 'y']+"(new ResourceLocation(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+".get(), GenerationStage.Decoration."+generation_step+", new StructureSeparationSettings(RepurposedStructures."+config_category+"."+config_spawnrate_entry+".get(), (int) (RepurposedStructures."+config_category+"."+config_spawnrate_entry+".get() * 0.5f), "+spacing_seed+"));")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'configured_structure_int.txt'), "a")
    currentfile.write("\n    public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> "+structure_variable_name+" = RSStructures."+structure_variable_name+".get().configure(IFeatureConfig.NO_FEATURE_CONFIG);")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'configured_structure_registration.txt'), "a")
    currentfile.write("\n        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, \""+structure_registry_name+"\"), "+structure_variable_name+");")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'tags.txt'), "a")
    currentfile.write("\n        addTags(RSStructures."+structure_variable_name+".get(), Stream.of("+innate_tags+").collect(Collectors.toSet()));")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'spawning.txt'), "a")
    currentfile.write("\n\n        if (RepurposedStructures.RSMainConfig."+config_spawnrate_entry+".get() != 1001) {")
    currentfile.write("\n            if (() &&")
    currentfile.write("\n                    (event.getName().getNamespace().equals(\"minecraft\") || RepurposedStructures."+config_category+"."+config_modded_biome_entry+".get())) {")
    currentfile.write("\n                event.getGeneration().getStructures().add(() -> RSConfiguredStructures."+structure_variable_name+");")
    currentfile.write("\n            }")
    currentfile.write("\n        }")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'config.txt'), "a")
    currentfile.write("\n\n		public ConfigValueListener<Integer> "+config_spawnrate_entry+";")
    currentfile.write("\n		public ConfigValueListener<Boolean> "+config_modded_biome_entry+";")
    currentfile.write("\n")
    currentfile.write("\n					"+config_spawnrate_entry+" = subscriber.subscribe(builder")
    currentfile.write("\n						.comment("+bend(50, config_spawnrate_comment)+")")
    currentfile.write("\n						.translation(\"repurposedstructures.config."+config_subcategory+"."+config_spawnrate_entry.lower()+"\")")
    currentfile.write("\n						.defineInRange(\""+config_spawnrate_entry+"\", "+spacing_default_value+", 1, 1001));")
    currentfile.write("\n")
    currentfile.write("\n					"+config_modded_biome_entry+" = subscriber.subscribe(builder")
    currentfile.write("\n						.comment("+bend(50, config_modded_biome_comment)+")")
    currentfile.write("\n						.translation(\"repurposedstructures.config."+config_subcategory+"."+config_modded_biome_entry.lower()+"\")")
    currentfile.write("\n						.define(\""+config_modded_biome_entry+"\", true));")
    currentfile.write("\n")
    currentfile.close()

    currentfile = open(os.path.join('forge', 'en_us_translations.txt'), "a")
    currentfile.write("\n  \"advancements.repurposed_structures."+structure_registry_name+".title\": \""+advancement_title+"\",")
    currentfile.write("\n  \"advancements.repurposed_structures."+structure_registry_name+".description\": \""+advancement_description+"\",")
    currentfile.close()


    print("\n\nFINISHED!")
    print("\nRESTARTING!\n\n")
