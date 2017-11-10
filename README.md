# -Music-Recommendation-System

A Music Recommendation System using Reinforcement Learning 

Α music recommendation system developed mainly in Java. This system generates a music playlist, with songs based on user inferred preferences.

Main idea:

This system generates a music playlist, with songs based on user inferred preferences. Each song is described by 10 descriptors of the Million Song Dataset (https://labrosa.ee.columbia.edu/millionsong/). K-means clustering was used in order to narrow down the descriptors search space. Firstly, the original file of 1.000.000 songs devided into 10 big clusters. Then, each of these 10 clusters(each contains about 100.000 songs), is devided into 100 subcategories (each contains about 1000 songs).Finally, each of these 100 subCategories, is devided into 4 clusters(each contains about 250 songs and they used in possible actions of the agent). When the application starts, the user is asked to enter his favorite artist. The artist is imported and the algorithm searches in the original file to find in which of the 10 clusters the artists belongs (main category). Then, the 100 subcategories of the main category, defines the agent's search space. A random subcategory is selected, from which the exploration begins. The implementation of this system, was based on Q-learning (ε-greedy) algorithm. In order to take the desicion in which state is going to transit, the algorithm takes the array Q into consideration and takes the action of the state, with the max Q-value. Array Q, contains as rows the states of the agent and as columns the possible actions. The possible actions are six. So, being in one state, the algorithm can go:
  1. to the nearest neighboring state
  2. to the second nearest neighboring state
  3. to the same state, and go:
      3.1 to the first cluster of the state
      3.2 to the second cluster of the state
      3.3 to the third cluster of the state
      3.4 to the fourth cluster of the state
      
  If the selected possible action is to transit to the first or second nearest neighboring state, then the algorithm of nearest neighbors is implemented. After the transition has taken place, the song that will selected, will be the one, in which the descriptors are closer to the descriptors of the previous song. The process of finding the "nearest" track, is carried out using the euclidean distance.
The chosen song is added to the playlist. According to the time that the user allows to playback (until the "Next" button is pressed), the algorithm receives a reward and then the array Q is updated.

To play the audio, the agent was connected with the youtube api. Because of the fact, that the function "search" of youtube api, works with keywords, sometimes returns irrelevant videos. For that reason, the button "Not related video" was added in order to not get negative reward the subcategory of this song. 
