INSERT INTO blog_user (user_id, login_name, password, email, first_name, last_name, role, is_suspended  ) VALUES
			(3, 'Thor', '$2a$10$MU9Umjr6OKE3Dw7nvH7h2eLa2dh50jNGW/us30523q/XX4gHvuhmq', 'thor@avengers.com', 'Thor', 'son of Odin', 'REG_USER', false),
            (4, 'CaptainAmerica', '$2a$10$MU9Umjr6OKE3Dw7nvH7h2eLa2dh50jNGW/us30523q/XX4gHvuhmq', 'captainamerica@avangers.com', 'Steve',  'Rogers', 'ADMIN', false),
            (5, 'Hulk', '$2a$10$MU9Umjr6OKE3Dw7nvH7h2eLa2dh50jNGW/us30523q/XX4gHvuhmq', 'hulk@avangers.com', 'Bruce', 'Banner', 'MODERATOR', false),
            (6, 'BlackWidow', '$2a$10$MU9Umjr6OKE3Dw7nvH7h2eLa2dh50jNGW/us30523q/XX4gHvuhmq', 'blackwidow@avangers.com', 'Natasa', 'Romanoff', 'MODERATOR', false),
            (7, 'Hawkeye', '$2a$10$MU9Umjr6OKE3Dw7nvH7h2eLa2dh50jNGW/us30523q/XX4gHvuhmq', 'hawkeye@avangers.com', 'Clint' , 'Barton', 'REG_USER', false),
            (8, 'Loki', '$2a$10$MU9Umjr6OKE3Dw7nvH7h2eLa2dh50jNGW/us30523q/XX4gHvuhmq', 'loki@avangers.com', 'Loki' , '', 'REG_USER', true);



INSERT INTO blog_template (template_id, template_name, font_color, category) VALUES 
					(1, 'NewYork', 'blue', 'MINIMAL'),
                    (2, 'Asgard', 'silver', 'PLAYFUL'),
                    (3, 'Titan', 'purple', 'CASUAL'),
                    (4, 'Sakaar', 'blue', 'PLAYFUL');
                    


INSERT INTO blog(blog_id, blog_title, blog_owner_user_id, blog_template_template_id) VALUES
				(1, 'Thundersorms', 3, 1),
                (2, 'SHIELD', 4, 3),
                (3, 'Gladiator life', 5, 2),
                (4, 'Science', 5, 2),
                (5, 'Interrogations', 6, 1),
                (6, 'Tricks', 8, 4);



INSERT INTO blog_post (blog_post_id, blog_post_title, text, blog_post_status, blog_blog_id) VALUES
					(1, 'Two years', ' after the battle of Sokovia,', 'DRAFT', 1),
                    (2, 'Thor is imprisoned', ' by the fire demon Surtur, who reveals that Thors father Odin is no longer on Asgard.', 'POSTED', 2),
                    (3, 'He explains', ' that the realm will soon be destroyed during the prophesied Ragnarök, once Surtur unites his crown with the Eternal Flame that burns in Odins vault. ', 'INVISIBLE', 3),
                    (4, 'Thor frees himself', ' defeats Surtur and takes his crown, believing he has prevented Ragnarök.', 'POSTED', 4),
                    (5, 'Thor returns', ' to Asgard to find Heimdall gone and his estranged brother Loki posing as Odin.', 'DRAFT', 4),
                    (6, 'After exposing', ' Loki, Thor forces him to help find their father, and with directions from Stephen Strange at the Sanctum Sanctorum in New York City, they locate Odin in Norway. ', 'POSTED', 5),
                    (7, 'Odin explains', ' that he is dying, Ragnarök is imminent despite Thors efforts to prevent it, and his passing will free his firstborn child, Hela, from a prison she was sealed in long ago.', 'POSTED', 6);

  

 INSERT INTO comment (comment_id, blog_post_blog_post_id, commenter_user_id, preceding_comment_id, comment_text, is_visible) VALUES
                    (1, 1,4, NULL, 'Deciding to help Thor',true ),
                    (2, 1,5, 1, 'The Grandmaster orders 142 and Loki to find Thor',true ),
					(3, 1,6, NULL , ' and Hulk, but the pair come to blows and ',false ),
					(4, 1,7,2, 'Loki forces her to relive the deaths of her Valkyrie ', TRUE),
					(5, 2,5,NULL, 'companions at the hands of Hela. , she takes Loki captive. Unwilling to ',TRUE ),
					(6, 3,6,NULL , 'group with the means to steal', TRUE),
					(7, 4,4,NULL, ' one of the Grandmasters ships.', TRUE),
					(8, 4,5,NULL, 'They then liberate the other g', TRUE),
					(9, 4,6,8, 'ladiators who, incited by two aliens named K', FALSE),
					(10, 4,7,8, ' Korg and Miek, stage a revolution', TRUE);
                    
     
