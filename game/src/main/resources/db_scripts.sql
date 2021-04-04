create database cricket_scoreboard;
use cricket_scoreboard;

create table `game`
(`id` bigint(12) not null auto_increment,
`team1_name` varchar(255) not null,
`team2_name` varchar(255) not null,
`no_of_overs` int(2) not null,
`no_of_players_per_team` int(2) not null,
`created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id)
);

create table `team`
(`id` bigint(12) not null auto_increment,
`game_id` bigint(12) not null,
`no_of_players` int(2) not null,
`name` varchar(255) not null,
`batting_order` text ,
`created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id),
index `idx_game_id_name` (game_id,name)
);

create table `score_details`
(`id` bigint(12) not null auto_increment,
`batsmen_name` varchar(255) not null,
`score` int(2) not null,
`team_id` bigint(12) not null,
`ball_id` bigint(12) not null,
`valid_ball` tinyint not null,
`created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id),
index `idx_team_id_batsmen_name` (team_id,batsmen_name)
);

create table `player`
(`id` bigint(12) not null auto_increment,
`name` varchar(255) not null,
`game_id` bigint(12) not null,
`team_id` bigint(12) not null,
`playing` tinyint not null default 0,
`created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id),
index `idx_team_id_name` (team_id,name)
);

create table `ball`
(`id` bigint(12) not null auto_increment,
`game_id` bigint(12) not null,
`over_no` int(2) not null,
`score` int(2) not null,
`wide` tinyint not null default 0,
`no_ball` tinyint not null default 0,
`wicket` tinyint not null default 0,
`batsmen_player_id` bigint(12) not null,
`created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id),
index `idx_game_id_batsmen_player_id` (game_id,batsmen_player_id)
);

