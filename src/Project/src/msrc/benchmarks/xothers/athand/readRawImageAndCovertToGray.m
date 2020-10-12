function grayImage = readRawImageAndCovertToGray(filePathName)
    originalImage = imread(filePathName);
    grayImage = mean(originalImage, 3);
