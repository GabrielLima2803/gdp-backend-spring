INSERT IGNORE INTO roles (role_id, name) VALUES (1, 'admin');
INSERT IGNORE INTO roles (role_id, name) VALUES (2, 'basic');
INSERT IGNORE INTO subscriptions (
    id,
    name,
    price,
    description,
    duration_months,
    created_at,
    updated_at
) VALUES (
             1,
             'Free',
             0.00,
             'Plano Free',
             0,
             NOW(),
             NOW()
         );