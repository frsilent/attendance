attendance
==========
Spring 2013 Data Communications Class Project
Android application proof of concept for Attendance Checkin.

The following project works as a proof of concept for the use of low frequency NFC tags
to act as a new form of attendance verification for classrooms. With the use of an android device,
we can scan a students specifically encoded NFC tag(one time encode only) with their ID and send data 
to a web server where it is stored securely.  This allowes the Professor to check each day who has attended class
and who has not.  The web server part of the project is a simple django app that runs verification of the data server side,
and records a timestamp that can't be modified by the user.  A professor can login to the system, and view their class data
to see what students have attended and who havn't for that day, or for the whole semester. 


