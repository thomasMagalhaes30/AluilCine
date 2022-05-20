#!/bin/bash


usage() {
cat <<EOF
Usage : ${0##.*/} [movie,cinema,user,review,horaire]
EOF
}

[[ "$#" -eq 0 ]] && usage && exit 1

collection_name="$1"


case $collection_name in
	"movie"|"cinema"|"user"|"review"|"horaire")
		;;
	*)
		echo "La collection \"$collection_name\" n'existe pas à première vue"
		;;
esac

mongoexport -c "$collection_name" --authenticationDatabase aluilcine -u aluilcine --host=20.234.144.102:27017 -d aluilcine -o "$collection_name.json"
