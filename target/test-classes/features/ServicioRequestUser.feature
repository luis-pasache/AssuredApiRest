@todosLosEscenarios
Feature: Pruebas al servicio usuario

  @metodoGet
  Scenario: Listar todos los usuarios
    Given obtener usuarios
    When mostrar el listado de usuarios
    And validar el codigo de respuesta "200"
    Then validar el numero de usuarios

  @metodoGet
  Scenario: listar un solo usuario
    Given obtener un usuario por id "2"
    When mostrar al usuario
    And validar el codigo de respuesta "200"
    Then validar la informacion de usuario
      | email                  | nombre | apellido |
      | janet.weaver@reqres.in | Janet  | Weaver   |

  @metodoGet
  Scenario: usuario no registrado
    Given obtener un usuario por id "50"
    Then validar el codigo de respuesta "404"

  @metodoPost
  Scenario: registrar usuario
    Given no existe usuario registrado
    When registrar datos del usuario
      | nombre | puesto | codigo |
      | Luis   | QA     | 201    |
      | Diego  | QA     | 201    |
      | Yoel   | QA     | 201    |
    Then mostrar el registro
    And validar el codigo de respuesta "201"

  @metodoPut
  Scenario: Actualizar usuario
    Given que el usuario este registrado
    When actualizar datos del usuario
      | id | nombre    | puesto | codigo |
      |  2 | Alexander | Tester | 200    |
    Then validar el codigo de respuesta "200"
    And mostrar el registro