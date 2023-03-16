![The banner logo for Repurposed Structures](https://user-images.githubusercontent.com/40846040/212383632-2d628e1a-a086-490f-8863-823210407626.png)

# See the wiki for more details about this bee-tastic mod!

***

# MAVEN

For developers that want to add RS to their mod's workspace:

```
repositories {
  maven {
    url "https://nexus.resourcefulbees.com/repository/telepathicgrunt/"
  }
}
```

&nbsp;

(This format is now standardized for RepurposedStructures from 6.3.14 forward. See older branches's README.md for what the old format was) Don't forget to change \<modversion> with the actual latest version of this mod like `6.3.14` for example. Replace forge with your modloader and the mc version with the target mc version.

```
dependencies {
  ...
  FORGE: implementation fg.deobf("com.telepathicgrunt:RepurposedStructures:\<modversion>+1.19.3-forge")
  FABRIC: modImplementation "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.19.3-fabric"
  QUILT: modImplementation "com.telepathicgrunt:RepurposedStructures:\<modversion>+1.19.3-quilt"
}
```

&nbsp;

**FORGE ONLY: Add the mixingradle to your buildscript's dependencies block. These will allow Bumblezone's mixins to work. After you add the properties lines, refresh Gradle and run `genEclipseRuns` or `genIntellijRuns` or `genVSCodeRuns` based on what IDE you are using.**

https://github.com/SpongePowered/Mixin/wiki/Mixins-on-Minecraft-Forge#step-1---adding-the-mixingradle-plugin

```
buildscript {
  ...
  dependencies {
    classpath group: 'net.minecraftforge.gradle', name: 'ForgeGradle', version: '5.1.+', changing: true
    // MixinGradle:
    classpath 'org.spongepowered:mixingradle:0.7.+'
  }
}
```
