# my-common


#####上传空文件（用Git Bash Here打开执行）
find . \( -type d -empty \) -and \( -not -regex ./\.git.* \) -exec touch {}/.gitxxx \;

#####清除空文件
find ./ -type f -name '.gitxxx' -delete
