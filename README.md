# RandomDog
RandomDog App
RandomDog! is a small app that generates random images of dogs that are saved for viewing later!!
There will be three screens in this app:!
- Home screen: buttons that lead to other two screens!
- Generate Dogs! screen: !
  - Has a “Generate!” button that sends a request to a public dog images API at https://dog.ceo/api/breeds/image/random 
  - When the generate button is hit and the image data is good, it is displayed, and stored in a Cache!
  - The Cache:!- An LRU cache: Holds the 20 most recent image data generated from requests to the above API. Don’t worry about time/space eﬃciency here.!
  - Make sure this persists from app session to app session.!
- My Recently Generated Dogs! screen:!
  - Has a scrollable gallery of images that is created from the data held by the cache.!
  - Has a “Clear Dogs!” button that clears out the cache, and the gallery.!
  - The RGB values for the given buttons are R:66, G:134, B:244.
