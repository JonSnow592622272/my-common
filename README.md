# my-common


#####清除空文件夹下的“.gitxxx”文件（用Git Bash Here打开执行）
find ./ -type f -name '.gitxxx' -delete

#####使能够上传空文件夹（用Git Bash Here打开执行）
find . \( -type d -empty \) -and \( -not -regex ./\.git.* \) -exec touch {}/.gitxxx \;
