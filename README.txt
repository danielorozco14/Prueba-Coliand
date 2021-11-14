Buenas noches, mi nombre es Daniel Alejandro Orozco Orellana

RUTAS:

[GET] http://localhost:9191/api/v1/repositories
[POST] http://localhost:9191/api/v1/repositories
[GET] http://localhost:9191/api/v1//users/{username}
[PATCH] http://localhost:9191/api/v1/repos/{username}/{repoName}

IMPORTANTE:
EN EL ARCHIVO RepoService esta la variable TOKEN que se necesita proveer para que funcione la aplicacion.

Adjunto un ejemplo de como tiene que enviarse la data a la API para hacer las peticiones POST y PATCH:

{
    "name":"Prueba Coliand",
    "autoInit":true,
    "private":false,
    "gitignore_template":"nanoc"
}