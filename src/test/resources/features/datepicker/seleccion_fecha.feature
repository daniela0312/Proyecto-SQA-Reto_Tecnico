@smoke @regression
Feature: Selección de fechas en el Datepicker
  Como usuario
  Quiero poder seleccionar fechas en el datepicker
  Para validar que el campo muestra el valor esperado y manejar errores correctamente

  Background:
    Given que abro el datepicker


  Scenario Outline: Selección de una fecha específica en un mes distinto al actual
    When selecciono la fecha "<fecha>"
    Then debo ver el valor "<resultado>" en el campo
    Examples:
      | fecha       | resultado   |
      | 2026-01-15  | 01/15/2026  |

  # 2) Fecha en el mes actual
  Scenario Outline: Selección de una fecha en el mes actual
    When selecciono la fecha "<fecha>"
    Then debo ver el valor "<resultado>" en el campo
    Examples:
      # Ajusta el mes/año a tu mes actual si lo deseas
      | fecha       | resultado   |
      | 2025-09-10  | 09/10/2025  |
      | 2025-09-30  | 09/30/2025  |

  # 3) Fechas inválidas (pruebas negativas)
  Scenario Outline: Intento de seleccionar una fecha inválida
    When intento seleccionar la fecha invalida "<fecha_invalida>"
    Then el campo de fecha debe permanecer "<esperado>"
    Examples:
      # Días que no existen o formato inválido
      | fecha_invalida | esperado |
      | 2025-02-30     |          |
      | 2025-13-10     |          |
      | 2025-00-01     |          |
      | 2025-04-31     |          |

  # 4) Validación genérica del campo con distintos valores válidos
  Scenario Outline: Validación genérica del valor del campo con varias fechas válidas
    When selecciono la fecha "<fecha>"
    Then debo ver el valor "<resultado>" en el campo
    Examples:
      | fecha       | resultado   |
      | 2025-12-01  | 12/01/2025  |
      | 2024-02-29  | 02/29/2024  | # Año bisiesto
      | 2027-07-04  | 07/04/2027  |