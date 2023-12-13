[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/wTBA-Etm)

# Cloud storage Simulator CLI

This cli will simulate a cloud storage that have `10Mb` of disk storage. The storage will just be a logical disk, so it will never created.

All file that will be upload will be saved as key value, `key: name of the file.extension` and `value: path to the file`

We can store some type of files in the storage : 
- Image (.png, .jpeg, ...)
- Video (.mp4, .3gp, ...)
- Pdf (.pdf)
- Office files (.docs)

# Features 

What can we do with this : 
1. Upload file by `file type`, `name` and the `path` to the file.
2. Download file by it's name
3. View the list of all saved files
4. Remove file from the storage

Handle some error that may arise on these steps :
1. on upload 
    - it will verify if :
        - the path exist
        - the name and the path of the file is not already in the storage
        - the real file type correspond to the specified file type 
        - the file size is no greater than `10Mb`
        - the current used storage add to the file to upload is not greater than `10MB`

2. on download and on remove :
    - if will verify if :
        - the specified name exist
        - the path of the specified name exist 

3. every time :
    - check :
        - too many request
        - request time out
        - server down

# Team Members: 

- [ STD21001 - Fanjasoa ANDRIAMANJAKA](https://github.com/fanjasoa18)
- [STD21052 - Amour Bien Aimé RAMANANTSIRESY](https://github.com/amourRamanantsiresy)
- [ STD21081 - RAHARINAIVOSOA Radintsoa Mirado](https://github.com/mirado447)
- [ STD21102 - ]()
- [ STD21119 - ]()