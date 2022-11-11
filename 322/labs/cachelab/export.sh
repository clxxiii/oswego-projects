rm export.tar.gz
mkdir export
cp cachelab.c ./export
cp cachelab.h ./export
cp cachelab_readme.txt ./export
tar -czvf export.tar.gz ./export
rm -rf export