ALTER TABLE public.user_roles ADD CONSTRAINT user_roles_roles_fk FOREIGN KEY (role_id) REFERENCES public.roles(id) ON DELETE CASCADE ON UPDATE CASCADE ;
ALTER TABLE public.user_roles ADD CONSTRAINT user_roles_users_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE ON UPDATE CASCADE ;
