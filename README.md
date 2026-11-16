# RPG por turnos — Strategy, Factory Method y Decorator

Proyecto Maven (Java 11+). Abrir en IntelliJ: **File > Open** y seleccionar la carpeta (o `pom.xml`).

## Ejecutar
- Juego interactivo: ejecutar la clase `Main`.
- Corridas de ejemplo (las de `EJECUCION.txt`): ejecutar `Main` con el argumento de programa `--demo`
  (Run > Edit Configurations > Program arguments).

## Patrones
| Patrón | Clases |
|---|---|
| Strategy | `EstrategiaAtaque`, `AtaqueEspada`, `AtaqueHechizo`, `AtaqueFlecha`, `AtaquePunalada` |
| Factory Method | `CreadorPersonaje` → `CreadorGuerrero/Mago/Arquero/Picaro`; `CreadorEnemigo` → `CreadorOrco/Dragon/Goblin` |
| Decorator | `PersonajeDecorator` → `EspadaDecorator`, `ArmaduraDecorator`, `VenenoDecorator`, `AmuletoRegeneracionDecorator`, `BendicionDecorator` |

Equipamiento (códigos de menú): 1 Espada, 2 Armadura, 3 Veneno, 4 Amuleto de regeneración, 5 Bendición.
