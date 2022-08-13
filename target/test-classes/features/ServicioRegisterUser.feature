Feature: registrar un usuario exitosamente

  Scenario: registro exitoso
    Given usuario que no existe
    When registrar datos del usuario con correo
      | email              | password |
      | eve.holt@reqres.in | pistol   |
    And validar el codigo de respuesta exitoso "200"
    Then mostrar el registro guardado