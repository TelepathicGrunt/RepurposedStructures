package com.telepathicgrunt.repurposedstructures;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.datafixer.Schemas;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtIo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class StructureNBTDataFixer {


    public static void updateAllNBT() throws IOException {
        String mainPath = "C:\\Users\\Admin\\Documents\\PersonalFun\\Minecraft stuff\\JavaCodeMods\\ModdingWorkspace\\RepurposedStructures-Fabric";
        String resourcePath = mainPath+"\\src\\main\\resources\\data";

        List<File> files = new ArrayList<>();
        listf(resourcePath, files);
        for(File file : files){
            InputStream inputStream = new FileInputStream(file);

            File resultingFile = new File(mainPath+"//"+file.getAbsolutePath().split("resources\\\\")[1]);
            resultingFile.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(resultingFile);

            CompoundTag newNBT = updateNBT(inputStream);
            NbtIo.writeCompressed(newNBT, outputStream);
        }
    }

    //source: https://stackoverflow.com/a/14676464
    public static void listf(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile() && file.getName().contains(".nbt")) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files);
                }
            }
        }
    }


    public static CompoundTag updateNBT(InputStream structureInputStream) throws IOException {
        CompoundTag compoundTag = NbtIo.readCompressed(structureInputStream);
        return NbtHelper.update(Schemas.getFixer(), DataFixTypes.STRUCTURE, compoundTag, compoundTag.getInt("DataVersion"));
    }
}
