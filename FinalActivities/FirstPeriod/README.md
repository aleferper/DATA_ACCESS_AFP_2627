# Flights - Data Access Final Activity (First Period)

Author: Alejandro Ferrandiz Perez (AFP)

Delivery 1 (05/11): `--help` and `--add` options.

## Setup
1. Create the database with the script from AULES.
2. Edit `src/main/resources/db.properties` with your connection data.
3. Open the project in IntelliJ IDEA (Maven, Corretto 17) and set the program arguments.

## Examples
```
--help
--add xml/passengers_ok.xml
--add xml/passengers_error.xml
```
Events are logged in `flights.log`.
