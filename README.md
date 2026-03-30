![The banner logo for Repurposed Structures](https://user-images.githubusercontent.com/40846040/212383632-2d628e1a-a086-490f-8863-823210407626.png)

***

## COMPILEONLY MAVEN

For developers that want to add Repurposed Structures to their mod's workspace:

```gradle
repositories {
  maven {
     url "https://nexus.resourcefulbees.com/repository/telepathicgrunt/"
  }
}
```

&nbsp;

Don't forget to change &lt;modversion> with the actual latest version of this mod like `7.5.13` for example. (See older branches's README.md for their stuff)

```gradle
dependencies {
   ...
   NEOFORGE: 
     compileOnly fg.deobf("com.telepathicgrunt:RepurposedStructures:<modversion>+1.21.4-neoforge")
     
   FABRIC/QUILT: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:<modversion>+1.21.4-fabric"
     
   ARCH COMMON MODULE: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:<modversion>+1.21.4-common"
}
```

&nbsp;

***

## IMPLEMENTATION MAVEN

For developers that want to add Repurposed Structures to their mod's workspace:

```gradle
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

Don't forget to change &lt;modversion> with the actual latest version of this mod like `7.5.13` for example. (See older branches's README.md for their stuff)

```gradle
dependencies {
   ...
   NEOFORGE: 
     implementation fg.deobf("com.telepathicgrunt:RepurposedStructures:<modversion>+1.21.4-neoforge")
     
   FABRIC/QUILT: 
     modImplementation "com.telepathicgrunt:RepurposedStructures:<modversion>+1.21.4-fabric"
     modImplementation "maven.modrinth:midnightlib:1.5.7-fabric" 
   
   ARCH COMMON MODULE: 
     modCompileOnly "com.telepathicgrunt:RepurposedStructures:<modversion>+1.21.4-common"
}
```

-------

## YourKit

![https://www.yourkit.com/images/yklogo.png](https://www.yourkit.com/images/yklogo.png)

YourKit supports open source projects with innovative and intelligent tools
for monitoring and profiling Java and .NET applications.
YourKit is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a>,
<a href="https://www.yourkit.com/dotnet-profiler/">YourKit .NET Profiler</a>,
and <a href="https://www.yourkit.com/youmonitor/">YourKit YouMonitor</a>.

-------
