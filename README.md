cyberdojo_sync
==============

A tool to sync cyber-dojo.com katas with your local machine.

Instructions

Requires java and maven installed to build.
Requires Java and Firefox to run
building an executable jar by "mvn package"

i have created a little bash script to deploy jar and cdsync 
(bash script to start application) into ~/bin/ 
cdsync looks for jar in ~/bin/

Usage

* Go to cyber-dojo.com and create a kata session.
* Copy the url of your kata session to the clipboard
* Create a folder to host the files
* Go to host folder and type "cdsync get"
* Answer y at the prompt and press enter
* Paste the url into the terminal and press enter
* Open up the files in an editor of your liking
* Code away
* when you want to run the tests goto terminal and type "cdsync test" 
  the files will be uploaded, the tests are run and the output is downloaded


if you want the firefox window to stay open you can start a continous run of cdsync 

* cdsync start

Commands accepted at the prompt are
* get
* test
* quit
pressing enter at the prompt without writing a command repeats last command 

  
