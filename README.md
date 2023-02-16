# MusicWiki App

MusicWiki is an Android app that allows users to explore top genres, albums, tracks, and artists in the music industry. The app uses the Last.fm API to fetch data about various genres, albums, and artists.

# Features

The following are the features of the MusicWiki app:
* Users can see all the top genres that are currently trending.
* When the user clicks on a genre, they will navigate to the genre detail page where they can see the genre description, list of top albums, list of top tracks, and list of top artists.
*Clicking on an album from the top albums will show the album details page where the user can see the album name, artist name, total play count, published date, the album description, and genres in that album. From here, clicking on the genre will again launch the same flow with genre details.
*Clicking on an artist from the top artists will show the artist detail screen where the user can see the artist's name, image, artist bio, total followers, total play count, list of top albums, list of top tracks, and list of genres.
*In artist info, clicking on albums will navigate to the album details page.
*In artist info, clicking on the genre will navigate to the genre details page.

# Steps Taken
The following are the steps that I have taken while making the MusicWiki app:

*I went through the requirements and understood all the details, including design files.
*I checked the Last.fm API documentation and created an account to get the API key.
*I tested all the APIs required on Postman to ensure that they are working correctly.
*I looked for design ideas related to music apps on various websites.
*I got the idea of showing only a little description text and adding the text "read more" for a user to open a bottom sheet where they can read the full description.
*I designed some of the app's screens myself on Figma.
*I started developing the app and got the necessary resources, such as Retrofit and Navigation framework.
*I created layout files based on my designs.

# Architecture

I have used the MVVM (Model-View-ViewModel) architecture pattern for developing the MusicWiki app. This architecture pattern helps to separate the user interface (View) from the business logic (ViewModel) and the data (Model) layer. This separation of concerns makes the app easier to maintain and test.

# Decisions & Assumptions Made

The following are the decisions and assumptions that I made while developing the MusicWiki app:

*For placing the "read more" option, if we get a lengthy description, it doesn't seem accurate to display that much text on the direct screen. So, I added the "read more" option by which the user will see a bottom sheet of the full description.
*While testing different APIs, I also got to know that for some artists, their wiki or bio is not in the response, which can cause some bugs. So, I have added some null checks for that.

# Figma Design Image

Here's the Figma design image that I have used for developing the MusicWiki app:

