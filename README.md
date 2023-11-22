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

Don't forget to change \<modversion> with the actual latest version of this mod like `7.2.0` for example. (See older branches's README.md for their stuff)

```
dependencies {
   ...
   NEOFORGE: 
     compileOnly fg.deobf("com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.2-neoforge")
     
   FABRIC/QUILT: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.2-fabric"
     
   ARCH COMMON MODULE: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.2-common"
}
```

&nbsp;

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

Don't forget to change \<modversion> with the actual latest version of this mod like `7.2.0` for example. (See older branches's README.md for their stuff)

```
dependencies {
   ...
   NEOFORGE: 
     implementation fg.deobf("com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.2-neoforge")
     
   FABRIC/QUILT: 
     modImplementation "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.2-fabric"
     modImplementation "maven.modrinth:midnightlib:1.4.1-fabric" 
   
   ARCH COMMON MODULE: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.20.2-common"
}
```
