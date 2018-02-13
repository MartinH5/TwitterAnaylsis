# TwitterAnaylsis

## How to set it up:

### 0. Prerequisite

You need mongodb running on your local machine.
Maven has to be installed on your machine.

### 1. Download Zip-File:

We will be needing this file:
http://cs.stanford.edu/people/alecmgo/trainingandtestdata.zip

### 2. Extract File and Access File

Unzip the file and open terminal in the follower where you'll find the content of the file.

### 3. Setup Data

In the folder with the file enter the following command:
```
sed -i '1s;^;polarity,id,date,query,user,text\n;' training.1600000.processed.noemoticon.csv
```
### 4. Import Data

Afterwards type the following command to import the data:

```
mongoimport --drop --db social_net --collection tweets --type csv --headerline --file training.1600000.processed.noemoticon.csv
```

### 5. Get the Project

Now we simply need to have the project on our machine, you can either download it from here
or use:
```
git clone https://github.com/MartinH5/TwitterAnalysis
```
### 6. Run it

Navigate to the project (cd /TwitterAnalysis/).

To run the project from the command line use:

```
mvn exec:java -Dexec.mainClass=com.mycompany.twitteranalysis.Control -Dexec.args="UserCount"
```
You can use the following commands as argument:

* Amount of users:  "UserCount"
* Top 10 linkers:   "TopLinkers"
* Top 5 mentioned:  "TopMentioned"
* Top 5 Active:     "TopActive"
* Top 5 Negative:   "TopNegative"
* Top 5 Positive:   "TopPositive" 


