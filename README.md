# Meme-Spigot

A Spigot coding series dedicated to just being a meme. This repository holds three small meme
plugins, updated to build against the latest Paper API.

## Plugins

| Plugin | Description |
| ------ | ----------- |
| [CoronaVirus](#coronavirus) | Spreads a fake "Corona Virus" between players |
| [Gravity](#gravity) | Launches players into the air when they stand still |
| [TNTFall](#tntfall) | Randomly primes TNT when players place blocks |

### CoronaVirus

A meme pandemic simulator. An OP starts the outbreak with `/cv`, which infects the first few online
players. From there the virus spreads on its own:

- Any player within 6 blocks of an infected player becomes infected.
- Any player damaged by an infected player becomes infected.

Infected players receive a (practically) permanent Poison effect, and when their health drops to half
a heart or less they are told they have died to the RONA.

**Commands**

| Command | Permission | Description |
| ------- | ---------- | ----------- |
| `/cv` | OP only | Starts the Corona Virus outbreak |

### Gravity

Watches the position of every online player. If a player stays in the same block for 5 seconds they
are launched into the air with an explosion sound and a broadcast message.

### TNTFall

Whenever a player places a block there is a 1 in 11 chance that primed TNT spawns at that spot,
accompanied by a broadcast message.

## Requirements

- **Java 25** or newer
- **Paper 26.3** or newer (compiled against `paper-api 26.3.build.246-stable`)
- **Maven 3.6.3** or newer (only needed to build)

## Building

Each plugin is a standalone Maven project. From the root of the repository:

```bash
cd CoronaVirus
mvn clean package
```

Repeat for `Gravity` and `TNTFall`. The compiled jar is written to each module's `target/` folder.

> The old builds copied the jar straight into a hardcoded local server `plugins` folder via the
> maven-jar-plugin `outputDirectory`. That has been removed so the build is portable. If you want it
> back, set `outputDirectory` in the maven-jar-plugin configuration of the pom.

## Installing

1. Build the plugin(s) with Maven.
2. Copy the jar from `<plugin>/target/` into your server's `plugins/` folder.
3. Start (or restart) the server.

## License

MIT - see [LICENSE](LICENSE).
