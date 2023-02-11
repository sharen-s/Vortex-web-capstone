BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO forum (name, description, rules)
    VALUES ('Techno', 'Join our discussion on today''s hottest techno DJ''s and their best tracks', 'No hateful language or illegal sharing of music'),
            ('HBO Hits', 'What''s your current favorite HBO show? Share with us, we need recs!', 'No hateful language or sharing of illegal videos'),
            ('Survivor', 'Are you a survivor superfan? We want to hear about your favorite seasons, players, and cutthroat tribal councils', 'No hateful language or sharing of illegal video'),
            ('NFL', 'Who is your favorite team? Who are you betting on this year?', 'No hateful language and NO EAGLES FANS. jk....'),
            ('Cat Club', 'Share your kitty pics! We need them.', 'No inappropriate media or hate speech');


INSERT INTO post (forum_id, user_id, title, body, img_url, up_votes, down_votes)
	VALUES (1, 1, 'Techno-paleontology Uni student slams tiktoker for spreading misinformation', 'If you go watch it again you can see his subtly tips his hand early on with "techno-paleontology". Brilliant', NULL, 150, 80), 
	(1,1, 'Hey Mr Technodad!', 'I`m not sure if you`ll see this or not, but Techno`s Discord Nitro is still going/is being payed for. You may want to try to get on his account so you can disable it.',NULL, 300, 400),
	(1,1, 'Hey, I`m sorry for Interrupting. Please contact me if you are a builder.','I look for a team to build the biggest Techno memorial ever made in minecraft. Without him I would not be playing this game and I want to pay tribute in the best way we can: A memorial from the best builders in his community.',NULL, 189, 1),
	(1,1, 'I just found this old tweet','it broke my heart. Rest in Peace Techno, you will never be forgotten.',NULL, 10, 200),
	(1,1, 'So I was looking for a list of Techno`s accomplishments on Wikitubia and, uh... ','I think his genius goes back further than we ever anticipated.',NULL, 198, 0),
	(2,1, '“Sharp Objects” is seriously one of most quality HBO shows I’ve ever seen', 'It stars Amy Adams and I think it might be the best role of her career. In the limited series she plays a very mentally ill, alcoholic journalist who has to return to her small home town in Missouri to report to on the cases of one murdered and one missing young girl. But it’s so much more than just a whodunit. The twists are amazing. Anyone else seen it and love it as much as I did?',NULL, 1320, 200), 
	(2,1, 'The Last of Us tv show is amazing!!','I was a bit uncertain about Ellie`s actress at first, but she`s pretty good. Pedro Pascal was fantastic and I love how close it`s staying to the video game. Can`t wait till next week!!',NULL, 1506, 900),
	(2,1, '‘Westworld’ Canceled at HBO After Four Seasons','Disappointed that I watched four seasons and won’t get to see the conclusion that was said to be season five.',NULL, 187, 45),
	(2,1, '‘House of the Dragon’ Premiere Draws Nearly 10 Million Viewers, Biggest HBO Series Premiere Ever','The whole premise of this show is based off 4 chapters in a book, this is going to be all on GRRM and the show runners. They say it will only go for 4 seasons, which is a good length. If they start to bank on it expect it to go to 5 and thats when it will suffer. Hopefully they are dead set on 4 seasons.',NULL, 392, 99),
	(3,1, 'Fun fact: ____ played, statistically speaking, the worst game in Survivor history.', 'Me! I did! So we talk a lot about the Survivor "perfect game", right? It`s the rare feat that occurs when a contestant receives none of the elimination votes, and then gets every single jury vote. In all of Survivor history, this has only occurred two times (JT and Cochran). But the REVERSE of this, where a contestant receives EVERY SINGLE elimination vote and then proceeds to get no jury votes (spoilers, lol) has only occurred once - Zach Wurtenberger, season 42. I hereby proclaim that I played the first truly Imperfect Game in Survivor history.
In all seriousness, thank you all for the ride! Enjoy tonight`s episode, it should be a fun one. I can`t wait to watch it.',NULL,2398, 1000), 
	(3,1, 'The new segment before Final Tribal is the best addition to the show in a long time','The addition of the jury not only telling us what they want and don`t want to hear, but also their perceptions of players before FTC. Before tonight this was left to the extra ponderosa videos. This is an incredible inclusion and will likely result in less anger at winners, and more understanding of how some lost. (IE Xander and Erika last season)
Thoughts?',NULL,2373, 2000),
	(3,1, 'No matter what happened tonight, we can all agree on one thing...','THAT`S NOT WHAT THE WORD "GASLIGHTING" MEANS, HAI. QUIT THROWING THAT WORD AROUND LIKE IT`S NOTHING. Gaslighting is what abusers do to their victims. What Romeo did was manipulation. THEY. ARE. TWO. DIFFERENT. THINGS.
I lost a lot of respect for Hai tonight because of that, tbh.',NULL,729, 300),
	(3,1, 'Can we give a quick moment of love to….','Owen? I think he gave one of the most grounded, self-aware losing FTC performances we’ve seen. He acknowledged his flaws, didn’t overhype himself, and stayed true to the reality of what his game was. It’s a commendable skill and trait to know yourself and your situation as well as he did all season. I think 43 was so much better off for his season-long presence, both for his story arc and for his next-level abilities as a narrator!',NULL,813, 900),
	(4,1, '[Condotta] Geno Smith asked today', 'whether he ever got frustrated during his seven years as a backup says no: "My tough times would be a dream to someone else."',NULL,231, 86),
	(4,1, '[Weprin] Oh wow','Brian Flores suing the NFL and NY Giants, alleging racism in hiring. Includes private texts from Bill Belichick',NULL,3297, 4000),
	(4,1, '[TMZ]','Teddy Bridgewater is calling on his fellow NFL players to quit pretending to be "gangsta" and start acting like role models.',NULL,12, 99),
	(4,1, '[McAfee]','After the NFL banned his use of team logos: "I don`t think we`ve really touched on many subjects that we could have...I think we`ll do that this offseason...like insurance and CTE and concussions"',NULL,23, 0),
	(5,1, 'Aww', NULL, 'https://images.unsplash.com/photo-1573865526739-10659fec78a5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Y2F0fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=400&q=60', 97, 50),
	(5,1, 'This picture is the perfect way to describe a cat', NULL, 'https://images.unsplash.com/photo-1543852786-1cf6624b9987?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGNhdHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=400&q=60',203820, 15000),
	(5,1, 'I found a cat. I don`t like cats, Now i have a cat i guess.', NULL, 'https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8Y2F0fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=400&q=60',675,80),
	(5,1, 'astronaut!',NULL, 'https://images.unsplash.com/photo-1618500299034-abce7ed0e8df?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8YXN0cm9uYXV0JTIwY2F0fGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=400&q=60',30, 200);
	
INSERT INTO moderator VALUES 
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5);

INSERT INTO comment (user_id, post_id, body)
	VALUES 
	(1,1, 'Techno is a genre of electronic dance music characterized by a repetitive beat and minimalistic structure.'),
	(1, 2, 'Detroit techno pioneers like Juan Atkins, Derrick May, and Kevin Saunderson helped shape the genre in the 80s and 90s.'),
	(1, 3, 'Techno music often serves as the soundtrack for underground dance parties and raves, creating a shared experience for fans and dancers alike.'),
	(1, 4, 'Today, techno continues to evolve and incorporate elements from other genres, making it a constantly evolving and innovative musical genre.'),
	(1, 5, 'Techno cat!'),
	(1, 6, 'Game of Thrones is a must-watch for fans of epic fantasy and political intrigue.'),
	 (1, 7, 'The Sopranos redefined the crime drama genre with its deep character development and captivating storyline.'),
	(1, 8, 'Big Little Lies is a phenomenal exploration of relationships, motherhood, and the darker sides of human nature.'),
	(1, 9, 'Westworld is a mind-bending sci-fi series that questions the nature of reality and consciousness.'),
	(1, 10, 'Some of the greatest players in NFL history include Jerry Rice, JaMarr Chase, Joe Burrow, Peyton Manning, and Lawrence Taylor, among many others.'),
	(1, 11, 'The NFL is the highest level of professional American football, with 32 teams competing annually for the Vince Lombardi Trophy.'),
	(1, 12, 'The NFL season runs from September to February, with each team playing 16 regular season games and the top teams advancing to the playoffs.'),
	(1, 13, 'The Super Bowl, held annually on the first Sunday in February, is the biggest event in American sports and attracts millions of viewers worldwide.'),
	(1, 14, 'The refs blew it the AFC champioship.'),
	(1, 15, 'Cats are popular pets known for their independent personalities, affectionate behavior, and playful antics.'),
	(1, 16, 'Domesticated cats are descendants of African wildcats and have been kept as pets for thousands of years.'),
	(1, 17, 'Cats are known for their grooming habits and use their rough tongues to clean their fur and remove loose hair.'),
	(1, 18, 'There are many different breeds of cats, each with their own unique physical and personality traits, such as Siamese, Persian, and Bengal cats.'),
	(1, 19, 'Survivor is a reality TV show where contestants are stranded on a deserted island and must outwit, outplay, and outlast each other to win a cash prize.'),
	(1, 20, 'The show, which first aired in 2000, has had over 40 seasons and is considered one of the pioneers of the reality TV genre.'),
	(1, 21, 'Contestants face challenges, vote each other off, and form alliances in a cutthroat competition to become the sole survivor and win the grand prize.');

COMMIT TRANSACTION;
