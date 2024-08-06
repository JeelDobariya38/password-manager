# Database Design & Guide

In this file, You will find the general structure & format in which app stores users data. Also you will you will find different schema of database.

> [!NOTE]
> Database schema versioning system is completely serperte from the app versioning system.

**Database**: Sqlite3(`master.db`), Jsonfile(`sys.db`)

## sys.db
sys.db is system db in json file where we store all the important data specfic to app and this data is usefull, during the schema updation process. this info is use by app updation module to change db schema when jump from one version to another.

> [!IMPORTANT]
> System database schema must not be alter between the app version.
> It allowed to add data but, not allowed to remove existing as different version of app rely on same data for there effective operation.

```json
{
  "app-name": "password-manager",
  "app-version": "v0.0.0-Alpha",
  "db-schema-version": "v1",
  "installed-at": "dd/mm/yy",
  "update-at": [
    {
      "from": "v0.0.0-alpha",
      "to": "v0.1.0-Alpha",
      "on": "dd/mm/yy"
    }
  ],
  "app-setting": {
    "extensive-mode": false,
    "prefer-dark-mode": true,
    "external-db": {
      "uri": "postgresql://username:password@host:port/dbname[?paramspec]",
      "server": "postgresql"
      ....
    }
  }
  ....
}
```

## master.db

It is a database where user info is stored. It schema changes often between the app version based on change in business requirements. Here, in this section you will find all the version of database schema & will also find info on how we adopated to the new schema.

### Current Database Design (v1)

| Tables      | Description                  |
| ----------- | ---------------------------- |
| `passwords` | For storing password entity. |

#### passwords table

| Fields      | Property    | Constraints        | Description                                                |
| ----------- | ----------- | ------------------ | ---------------------------------------------------------- |
| `id`        | Number      | PRIMARY KEY        | --                                                         |
| `domain`    | VarChar(40) | NOT NULL           | domain name to which password enitity is associated with   |
| `username`  | VarChar(60) | NOT NULL           | username on that domain. email can be also used as a value |
| `password`  | VarChar(60) | NOT NULL           | password on that domain                                    |
| `createdat` | Date        | --                 | --                                                         |
| `updatedat` | Date        | --                 | --                                                         |
