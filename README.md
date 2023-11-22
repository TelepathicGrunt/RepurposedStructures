![The banner logo for Repurposed Structures](https://user-images.githubusercontent.com/40846040/212383632-2d628e1a-a086-490f-8863-823210407626.png)

***

## COMPILEONLY MAVEN

For developers that want to add Repurposed Structures to their mod's workspace:

```
repositories {
  maven {
     url "https://nexus.resourcefulbees.com/repository/telepathicgrunt/"
  }
}
```

&nbsp;

Don't forget to change \<modversion> with the actual latest version of this mod like `7.1.10` for example. (See older branches's README.md for their stuff)

```
dependencies {
   ...
   FORGE: 
     compileOnly fg.deobf("com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-forge")
     
   FABRIC: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-fabric"
     
   QUILT: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-quilt"
     
   ARCH COMMON MODULE: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-common"
}
```

&nbsp;

**FORGE ONLY: Add the mixingradle to your buildscript's dependencies block. These will allow Repurposed Structures's mixins to work. After you add the properties lines, refresh Gradle and run `genEclipseRuns` or `genIntellijRuns` or `genVSCodeRuns` based on what IDE you are using.**

https://github.com/SpongePowered/Mixin/wiki/Mixins-on-Minecraft-Forge#step-1---adding-the-mixingradle-plugin

```
buildscript {
   ...
   dependencies {
      // MixinGradle:
      classpath 'org.spongepowered:mixingradle:0.7.+'
   }
}
```

***

## IMPLEMENTATION MAVEN

For developers that want to add Repurposed Structures to their mod's workspace:

```
repositories {
    maven {
     url "https://nexus.resourcefulbees.com/repository/telepathicgrunt/"
    }
    
    // Fabric/Quilt only
    maven {
        url = 'https://api.modrinth.com/maven/'
        content {
            includeGroup 'maven.modrinth'
        }
    }
}
```

&nbsp;

Don't forget to change \<modversion> with the actual latest version of this mod like `7.1.10` for example. (See older branches's README.md for their stuff)

```
dependencies {
   ...
   FORGE: 
     implementation fg.deobf("com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-forge")
     
   FABRIC: 
     modImplementation "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-fabric"
     modImplementation "maven.modrinth:midnightlib:1.4.1-fabric" 
   
   QUILT: 
     modImplementation "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-quilt"
     modImplementation "maven.modrinth:midnightlib:1.4.1-fabric" 
     
   ARCH COMMON MODULE: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.1-common"
}
```

&nbsp;

**FORGE ONLY: Add the mixingradle to your buildscript's dependencies block. These will allow Repurposed Structures's mixins to work. After you add the properties lines, refresh Gradle and run `genEclipseRuns` or `genIntellijRuns` or `genVSCodeRuns` based on what IDE you are using.**

https://github.com/SpongePowered/Mixin/wiki/Mixins-on-Minecraft-Forge#step-1---adding-the-mixingradle-plugin

```
buildscript {
   ...
   dependencies {
      // MixinGradle:
      classpath 'org.spongepowered:mixingradle:0.7.+'
   }
}
```
