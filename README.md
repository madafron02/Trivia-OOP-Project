
## Description of project
this application provides a small quiz to test people's awareness of energy consumption. Our goal is for
players to answer different kinds of questions about energy consumption, and use jokers and
emojis to interact with other players during gameplay.
## Group members

| Profile Picture | Name | Email |
|---|---|---|
| ![](https://secure.gravatar.com/avatar/3ef09295a2ed9695434a2a343cb60fb2?s=50&d=identicon) | Oana Madalina Fron | O.Fron@student.tudelft.nl |
| ![](https://secure.gravatar.com/avatar/d5227848b6b143ffb139aaf6a2403927?s=50&d=identicon) | Renyi Yang | R.Yang-7@student.tudelft.nl |
| ![](https://secure.gravatar.com/avatar/d896c8cf99a712914da3003bda432393?s=50&d=identicon) | Atanas Kichukov | A.B.Kichukov@student.tudelft.nl |
| ![](https://secure.gravatar.com/avatar/e82bf009a824344b850664a25b711cef?s=50&d=identicon) | Tim Appelman | T.C.Appelman@student.tudelft.nl |
| ![](https://secure.gravatar.com/avatar/03fe269b319a72b5978ac6cc7c73cd0e?s=50&d=identicon) | Robert Andrei Boboc | R.A.Boboc-1@student.tudelft.nl |
| ![](https://secure.gravatar.com/avatar/77f1ecd516417a2022c15ce98d23cd52?s=50&d=identicon) | Khalit Gulamov | K.Gulamov@student.tudelft.nl |
<!-- Instructions (remove once assignment has been completed -->
<!-- - Add (only!) your own name to the table above (use Markdown formatting) -->
<!-- - Mention your *student* email address -->
<!-- - Preferably add a recognizable photo, otherwise add your GitLab photo -->
<!-- - (please make sure the photos have the same size) --> 

## How to run it
prerequisite: java environment with jdk version above 16 
- download the newest release from activity bank and unzip it
- rename the folder "activity-bank" and put it under client/src/main/java/resources
- in server/src/main/ActivityParse.class line 26, change the path name according to the absolute path of file "activities.json"
- in client/src/main/java/client/Main.class line 46, change the path name according to the absolute path of file "Halloween Lobby Music.wav"
- open command prompt and travel under the project folder, type "gradlew build"
- use command "gradlew bootRun" to start the server
- use command "gradlew run" to start the client

## Preventing and handling errors
If there are no players in the database, you can not open the all-time leaderboard

## Copyright / License (opt.)
Copyright 2021 Delft University of Technology \
\
Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at \
http://www.apache.org/licenses/LICENSE-2.0 \
Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License.
