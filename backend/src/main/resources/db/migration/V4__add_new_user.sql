INSERT INTO public.users ("name",email,password)
	VALUES ('Admin','admin@teste.com','$2a$10$3FfDAJ0Wg4CgU/hnAQRCfOznfBwgr5Fm7/24mMAEZLmMI8wx3tly2');

INSERT INTO public.user_roles (user_id,role_id)
	VALUES (1,2);
