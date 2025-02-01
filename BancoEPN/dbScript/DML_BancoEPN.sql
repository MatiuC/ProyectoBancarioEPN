-- database: ../database/db_BancoEPN.sqlite
-- Insertar una persona (Cliente)
INSERT INTO Persona (
        nombre,
        apellido,
        fecha_nacimiento,
        direccion,
        email,
        telefono
    )
VALUES (
        'Juan',
        'Perez',
        '1990-05-15',
        'Calle Ficticia 123',
        'juan@email.com',
        '1234567890'
    );
-- Insertar un rol
INSERT INTO Rol (nombre_rol)
VALUES ('Cliente'),
    ('Asesor'),
    ('Cajero');
-- Asignar rol a una persona
INSERT INTO PersonaRol (persona_id, rol_id)
VALUES (1, 1);
-- Asignar rol 'Cliente' a la persona con id 1
-- Insertar una tarjeta
INSERT INTO Tarjeta (
        persona_id,
        numero_tarjeta,
        fecha_expiracion,
        pin,
        saldo
    )
VALUES (
        1,
        '1234567812345678',
        '2026-12-31',
        '1234',
        1000.00
    );
-- Crear sesión de cliente
INSERT INTO Sesion (persona_id, ip_origen)
VALUES (1, '192.168.1.1');
-- Crear balance para el cliente
INSERT INTO Balance (persona_id, balance_total)
VALUES (1, 1000.00);
-- Crear transacción de consumo
INSERT INTO Transaccion (origen_tarjeta_id, monto, tipo_transaccion)
VALUES (1, 200.00, 'consumo');