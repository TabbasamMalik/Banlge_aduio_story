# Banlge_aduio_story
simple audio streaming player

a simple audio streaming player for me? If yes then here is the details:

It will fully Rest API based, I have a WordPress website from where i will give you the API data, There is a already a website with that api data you can check it https://banglaaudiostories.com/ and the app will also similar of the site,

In the home page same as the website. The first 6 Menu is the Category Name, when click on any name it will open the audio list page of that category, when click on the audio link it will start to play.

7 th Link is the page where you can see all the audio list links

8 - 10 there are the page of category i mean there is a link name Writer when click on that it will show all the writer list, when click on the writer name will open the audio links of that writer. other links are same Radio Program and series.

these are the list of radio program you will get this list from this API :
https://onlinebanglaradio.com/wp-json/wp/v2/audio_cat?s976_per_page=500

In the every program has its own id which you also get from the API, such as

Sunday Suspense id: 166 taxonomy: audio_cat
Kuasha id: 477 taxonomy: audio_cat

So when you click on the Sunday Suspense it will open the Sunday Suspense Audio Story list. So you have to pass the id of this category to the audio story listing page. The Listing page API will be


https://onlinebanglaradio.com/wp-json/wp/v2/audio-story?audio_cat=166&per_page=100&page=1

If you look at the API there is audio_cat=166 So 166 is the id of Sunday suspense and audio_cat is its taxonomy name.

in the same way Radio Series List:
https://onlinebanglaradio.com/wp-json/wp/v2/audio_series?s976_per_page=500

Radio Series Audio Listing Page API:
https://onlinebanglaradio.com/wp-json/wp/v2/audio-story?audio_series=481&per_page=100&page=1

Here the taxonomy name audio_series the 481 is the id of the series name.

List of All Audio Writers:
https://onlinebanglaradio.com/wp-json/wp/v2/audio_writer?s976_per_page=500

Audio List of a writer:
https://onlinebanglaradio.com/wp-json/wp/v2/audio-story?audio_writer=488&per_page=100&page=1

Here the taxonomy name audio_qriter the 488 is the id of the writer.
