#!/bin/bash

usage() {
cat <<EOF
Usage : ${0##.*/}
	-c <collection> # la collection choisie [movie,cinema,review,user,horaire]
	-f <file> # le fichier json
EOF
}

[[ "$#" -lt 2 ]] && usage && exit 1

collection_name=""

while [ -n "$1" ]
do
	case "$1" in
		"-c")
			collection_name="$2"
			shift
			;;
		"-f")
			[[ ! -f "$2" ]] && echo "le fichier \"$2\" n'existe pas" && exit 1
			collection_name="${2%.json}"
			shift
			;;
	esac
	echo "collection_name $collection_name"
	mongoimport --host 20.234.144.102:27017 -u aluilcine --authenticationDatabase aluilcine --db aluilcine -c "$collection_name" "$collection_name.json"
	shift
done


