-- database: ../database/db_BancoEPN.sqlite
INSERT INTO
    EstadoCivil (nombre_estado_civil)
VALUES
    ('Soltero'),
    ('Casado'),
    ('Divorciado'),
    ('Viudo');

INSERT INTO
    Ciudad (nombre_ciudad)
VALUES
    ('Quito'),
    ('Guayaquil'),
    ('Cuenca'),
    ('Ambato');

INSERT INTO
    Rol (nombre_rol)
VALUES
    ('Administrador'),
    ('Cliente'),
    ('Cajero');
INSERT INTO
    Persona (
        cedula,
        nombre,
        apellido,
        sexo,
        estado_civil,
        ciudad,
        edad,
        fecha_nacimiento,
        direccion,
        correo,
        telefono,
        Rol
    )
VALUES
    (
        '0102030405',
        'Juan',
        'Perez',
        'Masculino',
        1,
        1,
        '30',
        '2025-02-10 14:30:45.123',
        'Av. Siempre Viva',
        'juan.perez@example.com',
        '0998765432',
        1
    ),
    (
        '0203040506',
        'Maria',
        'Gomez',
        'Femenino',
        2,
        3,
        '25',
        '2025-02-10 14:30:45.123',
        'Calle Falsa 123',
        'maria.gomez@example.com',
        '0987654321',
        3
    ),
    (
        '0304050607',
        'Carlos',
        'Lopez',
        'Masculino',
        1,
        1,
        '35',
        '2025-02-10 14:30:45.123',
        'Av. Principal 456',
        'carlos.lopez@example.com',
        '0976543210',
        2
    );

INSERT INTO
    CuentaBancaria (numeroCuenta, id_persona, saldo)
VALUES
    ('12345678', 1, 1000.00),
    ('87654321', 2, 2000.00),
    ('12345678', 3, 3000.00);

INSERT INTO
    cuentaCredito (
        numeroCuenta,
        id_persona,
        saldo_usado,
        limiteCredito
    )
VALUES
    ('12345678', 1, 500.00, 1500.00),
    ('87654321', 2, 1000.00, 2500.00);

INSERT INTO
    credenciales (id_persona, usuario, pass)
VALUES
    (1, 'juanp', 'password123'),
    (2, 'mariag', '123'),
    (3, 'carlosl', '123');

INSERT INTO
    Franquicia (nombre_franquicia)
VALUES
    ('Visa'),
    ('MasterCard');

INSERT INTO
    TipoTarjeta (nombre_tipo_tarjeta)
VALUES
    ('Debito'),
    ('Credito');

INSERT INTO
    Tarjeta (
        numero_tarjeta,
        fecha_expedicion,
        fecha_vencimiento,
        cvv,
        tipo_tarjeta,
        franquicia,
        Persona,
        id_cuentabancaria
    )
VALUES
    (
        '1234567812345678',
        '2023-01-01',
        '2026-01-01',
        '123',
        1,
        1,
        1,
        1
    ),
    (
        '8765432187654321',
        '2023-01-01',
        '2026-01-01',
        '456',
        2,
        2,
        2,
        2
    );

INSERT INTO
    TipoTransaccion (nombre_tipo_transaccion)
VALUES
    ('Deposito'),
    ('Transferencia'),
    ('Retiro');

INSERT INTO
    Transacciones (
        Origen,
        Destino,
        Monto,
        Fecha,
        Hora,
        TipoTransaccion,
        Descripcion
    )
VALUES
    (
        1,
        3,
        100.00,
        '2023-10-01',
        '12:00',
        2,
        'Transferencia de prueba'
    ),
    (
        1,
        3,
        100.00,
        '2023-10-01',
        '12:00',
        2,
        'Transferencia de prueba'

    ),
    (
        3,
        1,
        100.00,
        '2023-10-01',
        '12:00',
        2,
        'Transferencia de prueba'

    ),
    (
        3,
        1,
        100.00,
        '2023-10-01',
        '12:00',
        2,
        'Transferencia de prueba'

    ),
    (
        3,
        1,
        100.00,
        '2023-10-01',
        '12:00',
        2,
        'Transferencia de prueba'

    );