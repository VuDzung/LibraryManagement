USE [LibraryManagement]
GO
/****** Object:  Table [dbo].[LibRole]    Script Date: 05/07/2018 16:26:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibRole](
	[IdRole] [int] IDENTITY(1,1) NOT NULL,
	[NameRole] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[IdRole] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[LibRole] ON
INSERT [dbo].[LibRole] ([IdRole], [NameRole]) VALUES (1, N'ROLE_ADMIN')
INSERT [dbo].[LibRole] ([IdRole], [NameRole]) VALUES (2, N'ROLE_USER')
INSERT [dbo].[LibRole] ([IdRole], [NameRole]) VALUES (3, N'Test Role2')
SET IDENTITY_INSERT [dbo].[LibRole] OFF
/****** Object:  Table [dbo].[LibBook]    Script Date: 05/07/2018 16:26:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibBook](
	[IdBook] [int] IDENTITY(1,1) NOT NULL,
	[TitleOfBook] [nvarchar](50) NOT NULL,
	[Author] [nvarchar](128) NOT NULL,
	[PublishYear] [int] NOT NULL,
	[Image] [nchar](128) NOT NULL,
	[ShortDescription] [nvarchar](max) NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[IdBook] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[LibBook] ON
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (1, N'Harry Potter and the Sorcerer''s Stone', N'J.K. Rowling', 2015, N'http://books.google.com/books/content?id=wrOQLV6xB-wC&printsec=frontcover&img=1&zoom=1&source=gbs_api                           ', N'Turning the envelope over, his hand trembling, Harry saw a purple wax seal bearing a coat of arms; a lion, an eagle, a badger and a snake surrounding a large letter ''H''.')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (2, N'Harry Potter: Cinematic Guide: Albus Dumbledore', N'Scholastic', 2017, N'http://books.google.com/books/content?id=_FkqDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'This Cinematic Guide is the essential companion for fans of the Harry Potter films! Relive all the magic of Professor Dumbledore''s world with this guidebook featuring your favourite scenes and quotes from all eight Harry Potter movies!')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (3, N'The Cuckoo''s Calling', N'Robert Galbraith', 2014, N'http://books.google.com/books/content?id=BUTroQEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api                           ', N'Vietnamese version of the very popular adult debut mystery/detective novel by Robert Galbraith, a.k.a J. K. Rowling, featuring PI Cormoran Strike. Veitnamese translation by Ho Thi Nhu Mai. In Vietnamese. Annotation copyright Tsai Fong Books, Inc. Distributed by Tsai Fong Books, Inc.')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (4, N'Harry Potter Places', N'C. D. Miller', 2012, N'http://books.google.com/books/content?id=LEnHs53G0j0C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'Welcome to Harry Potter Places Book Three--Snitch-Seeking in Southern England and Wales, the third of five guidebooks designed to help Potterites visit all 68 UK Potterverse places: real-life places mentioned in JKR''s novels and movie film sites. Go to the website to find Book Three''s Table of Contents and learn about all the Harry Potter Places travel guidebooks."')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (5, N'Literary Allusion in Harry Potter', N'Beatrice Groves', 2017, N'http://books.google.com/books/content?id=8P8nDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'Literary Allusion in Harry Potter builds on the world-wide enthusiasm for J. K. Rowling’s series in order to introduce its readers to some of the great works of literature on which Rowling draws')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (6, N'Silent Hill', N'Bernard Perron', 2012, N'http://books.google.com/books/content?id=vc9lzxnJpccC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'The second entry in the Landmark Video Games series')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (7, N'Sir Arthur Conan Doyle', N'Conan Doyle', 2007, N'http://books.google.com/books/content?id=pvE6R9IvsXUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'This work aims to reveal Conan Doyle''s different activities. Apart from being a prolific author - his literary output included historical novels, science fiction and histories of the Boer War and the First World War, he was an early champion of the Channel Tunnel, he played cricket for the MCC, was an advocate of Spiritualism, introduced cross country skiing to Switzerland and he was acquainted with many public figures of the late Victorian and Edwardian period.')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (8, N'The Last Leaf', N'William Glennon', 1996, N'http://books.google.com/books/content?id=h52Bq1CIsDMC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'Nope')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (9, N'A Game of Thrones', N'Daniel Abraham', 2012, N'http://books.google.com/books/content?id=i_SorqUvsOEC&printsec=frontcover&img=1&zoom=1&source=gbs_api                           ', N'The kingdom of the royal Stark family faces its ultimate challenge in the onset of a generation-long winter, the poisonous plots of the rival Lannisters, the emergence of the Neverborn demons and the arrival of barbarian hordes. 75,000 first printing.')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (10, N'Snow White', N'Donald Barthelme', 2013, N'http://books.google.com/books/content?id=8t7APzmuRVEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api                 ', N'An inventive, satiric modern retelling of the classic fairy tale provides an incisive and biting commentary on the absurdities and complexities of modern life. In Snow White, Donald Barthelme subjects the traditional fairy tale to postmodern aesthetics. In the novel, the seven dwarves are men who live communally with Snow White and earn a living by washing buildings and making Chinese baby food. Snow White quotes Mao and the dwarves grapple with low self-esteem in this raucous retelling of the classic tale')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (11, N'Once Upon a Time', N'Charles Knight', 1854, N'http://books.google.com/books/content?id=8l4JAAAAQAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'Beauty and da beast')
INSERT [dbo].[LibBook] ([IdBook], [TitleOfBook], [Author], [PublishYear], [Image], [ShortDescription]) VALUES (12, N'Wuthering Heights', N'Emily Brontë', 1870, N'http://books.google.com/books/content?id=7wXy0iWQhmUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api                 ', N'Nope')
SET IDENTITY_INSERT [dbo].[LibBook] OFF
/****** Object:  Table [dbo].[Persistent_Logins]    Script Date: 05/07/2018 16:26:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Persistent_Logins](
	[UserName] [varchar](64) NOT NULL,
	[Series] [varchar](64) NOT NULL,
	[Token] [varchar](64) NOT NULL,
	[Last_Used] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Series] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LibUser]    Script Date: 05/07/2018 16:26:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibUser](
	[IdUser] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nchar](128) NOT NULL,
	[Password] [nchar](60) NOT NULL,
	[FullName] [nvarchar](128) NOT NULL,
	[IdRole] [int] NOT NULL,
	[LimitNumber] [int] NOT NULL,
	[BorrowedNumber] [int] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[IdUser] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[LibUser] ON
INSERT [dbo].[LibUser] ([IdUser], [UserName], [Password], [FullName], [IdRole], [LimitNumber], [BorrowedNumber]) VALUES (1, N'hqt95na@gmail.com                                                                                                               ', N'$2a$10$NUNGhhnirTGfym6rNSH06OYeFOpn2KPOEYkP3HWU7H0cMVyaqc10K', N'Hoàng Quốc Tuân', 1, 3, 0)
INSERT [dbo].[LibUser] ([IdUser], [UserName], [Password], [FullName], [IdRole], [LimitNumber], [BorrowedNumber]) VALUES (2, N'test@gmail.com                                                                                                                  ', N'$2a$10$TKWDwYqT9Cu2Rf9fxC86I.CyzGJ6nLTB1HHrCMYENdRPon36Merle', N'Tuan', 1, 3, 0)
SET IDENTITY_INSERT [dbo].[LibUser] OFF
/****** Object:  Table [dbo].[LibISBN]    Script Date: 05/07/2018 16:26:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibISBN](
	[ISBN] [nchar](15) NOT NULL,
	[IdBook] [int] NOT NULL,
	[Status] [bit] NOT NULL,
	[TotalBook] [int] NOT NULL,
	[NumberBooksBorrowed] [int] NOT NULL,
 CONSTRAINT [PK_ISBN] PRIMARY KEY CLUSTERED 
(
	[ISBN] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'0521272629     ', 11, 1, 23, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'0871296918     ', 8, 1, 12, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'184022570X     ', 7, 1, 96, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'6041045152     ', 3, 1, 40, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9780440423218  ', 9, 1, 20, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9780472051625  ', 6, 1, 69, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9781351978729  ', 5, 1, 49, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9781407177762  ', 2, 1, 40, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9781439144404  ', 10, 1, 30, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9781621074564  ', 10, 1, 20, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9781781100486  ', 1, 1, 50, 0)
INSERT [dbo].[LibISBN] ([ISBN], [IdBook], [Status], [TotalBook], [NumberBooksBorrowed]) VALUES (N'9781937520984  ', 4, 1, 50, 0)
/****** Object:  Table [dbo].[LibBorrowBook]    Script Date: 05/07/2018 16:26:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LibBorrowBook](
	[IdBorrow] [int] NOT NULL,
	[IdUser] [int] NOT NULL,
	[ISBN] [nchar](15) NOT NULL,
	[DateBorrow] [date] NOT NULL,
	[DateReturn] [date] NOT NULL,
 CONSTRAINT [PK_BorrowBook] PRIMARY KEY CLUSTERED 
(
	[IdBorrow] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Default [DF_BorrowBook_DateReturn]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibBorrowBook] ADD  CONSTRAINT [DF_BorrowBook_DateReturn]  DEFAULT (NULL) FOR [DateReturn]
GO
/****** Object:  Default [DF_ISBN_NumberBooksBorrowed]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibISBN] ADD  CONSTRAINT [DF_ISBN_NumberBooksBorrowed]  DEFAULT ((0)) FOR [NumberBooksBorrowed]
GO
/****** Object:  Default [DF_User_LimitNumber]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibUser] ADD  CONSTRAINT [DF_User_LimitNumber]  DEFAULT ((3)) FOR [LimitNumber]
GO
/****** Object:  Default [DF_User_BorrowedNumber]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibUser] ADD  CONSTRAINT [DF_User_BorrowedNumber]  DEFAULT ((0)) FOR [BorrowedNumber]
GO
/****** Object:  ForeignKey [FK_BorrowBook_ISBN]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibBorrowBook]  WITH CHECK ADD  CONSTRAINT [FK_BorrowBook_ISBN] FOREIGN KEY([ISBN])
REFERENCES [dbo].[LibISBN] ([ISBN])
GO
ALTER TABLE [dbo].[LibBorrowBook] CHECK CONSTRAINT [FK_BorrowBook_ISBN]
GO
/****** Object:  ForeignKey [FK_BorrowBook_User]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibBorrowBook]  WITH CHECK ADD  CONSTRAINT [FK_BorrowBook_User] FOREIGN KEY([IdUser])
REFERENCES [dbo].[LibUser] ([IdUser])
GO
ALTER TABLE [dbo].[LibBorrowBook] CHECK CONSTRAINT [FK_BorrowBook_User]
GO
/****** Object:  ForeignKey [FK_ISBN_Book]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibISBN]  WITH CHECK ADD  CONSTRAINT [FK_ISBN_Book] FOREIGN KEY([IdBook])
REFERENCES [dbo].[LibBook] ([IdBook])
GO
ALTER TABLE [dbo].[LibISBN] CHECK CONSTRAINT [FK_ISBN_Book]
GO
/****** Object:  ForeignKey [FK_User_Role]    Script Date: 05/07/2018 16:26:50 ******/
ALTER TABLE [dbo].[LibUser]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([IdRole])
REFERENCES [dbo].[LibRole] ([IdRole])
GO
ALTER TABLE [dbo].[LibUser] CHECK CONSTRAINT [FK_User_Role]
GO
