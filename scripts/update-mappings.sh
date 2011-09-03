#!/bin/bash

HIB=src/hibernate.cfg.xml
HIBN=src/hibernate.cfg.xml.new

more $HIB | perl -ne 'print if /^<\?.*/ .. /.*BEGIN.*/' | perl -ne 'print if not /.*BEGIN.*/' > $HIBN

echo "<!-- BEGIN -->" >> $HIBN

JAVA_FILES=`find ./src -name "*.java"`;

for f in $JAVA_FILES; do
    wordCount=`cat $f | grep @Entity | wc -l`;
    #echo -n "#"
    #echo "Word count is $wordCount";

    if [ $wordCount == 0 ]; then
        #echo "Class $f is not an entity";
        xxx=1;
    else
        #echo "Class $f is an entity";
        CLASS=`echo $f | cut -f 3- -d / | rev | cut -f 2- -d . | rev | tr / .`;
        echo "        <mapping class=\"$CLASS\" />" >> $HIBN;
    fi;
done

echo "<!-- END -->" >> $HIBN

echo "" >> $HIBN;
#echo "        <mapping resource=\"hibernate.query.xml\"/>" >> $HIBN
echo "    </session-factory>" >> $HIBN

echo "</hibernate-configuration>" >> $HIBN

rm $HIB
mv $HIBN $HIB


