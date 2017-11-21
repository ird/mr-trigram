This application implements a simple Hadoop MapReduce application to count the occurances of trigrams in files in an input folder, much like the traditional WordCount sample application. The following tutorial has been followed: https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html

### Example usage:
```
~/dev/hadoop-2.9.0/bin$ ./hadoop jar /home/david/dev/mr-trigrams/build/libs/mr-trigrams.jar uk.org.ird.TriCount ~/test ~/out
~/dev/hadoop-2.9.0/bin$ cat ~/out/part-00000 | grep -i "the"
THE	240
The	100
the	1128
```
