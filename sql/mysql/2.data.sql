-- Insert sample data
INSERT INTO am_user (id, user_name, nick_name, mobile_country_code, mobile_number, email, password, status, created_by, updated_by, is_deleted)
VALUES
(UUID(), 'johndoe', 'John', '+1', '5551234', 'john@example.com', 'password', 'ACTIVE', 'system', 'system', 0),
(UUID(), 'janedoe', 'Jane', '+1', '5555678', 'jane@example.com', 'password', 'ACTIVE', 'system', 'system', 0),
(UUID(), 'bobsmith', 'Bob', '+44', '7123456789', 'bob@example.com', 'password', 'ACTIVE', 'system', 'system', 0),
(UUID(), 'alicejones', 'Alice', '+44', '7123456790', 'alice@example.com', 'password', 'ACTIVE', 'system', 'system', 0),
(UUID(), 'davidsmith', 'David', '+44', '7123456791', 'david@example.com', 'password', 'ACTIVE', 'system', 'system', 0);
