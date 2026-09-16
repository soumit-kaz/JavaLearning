#!/usr/bin/env bash
set -u

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUT="$ROOT/out"
FILTER="${1:-}"
JAVAC="${JAVA_HOME:+$JAVA_HOME/bin/}javac"
JAVA="${JAVA_HOME:+$JAVA_HOME/bin/}java"

pass=0
fail=0
failed=()

rm -rf "$OUT"

while IFS= read -r dir; do
    rel="${dir#"$ROOT"/}"
    if [[ -n "$FILTER" && "$rel" != "$FILTER"* ]]; then
        continue
    fi
    dest="$OUT/$rel"
    mkdir -p "$dest"
    if ! (cd "$dir" && "$JAVAC" -encoding UTF-8 -d "$dest" ./*.java); then
        echo "COMPILE FAILED: $rel"
        fail=$((fail + 1))
        failed+=("$rel (compile)")
        continue
    fi
    for src in "$dir"/*.java; do
        cls="$(basename "$src" .java)"
        if ! (cd "$dir" && "$JAVA" -cp "$dest" "$cls" > "$dest/$cls.log" 2>&1); then
            fail=$((fail + 1))
            failed+=("$rel/$cls")
            echo "FAIL  $rel/$cls  (see out/$rel/$cls.log)"
        elif [[ "$rel" == *problems* ]] && grep -qE '(^|[^A-Za-z])(FAIL|MISMATCH)([^A-Za-z]|$)' "$dest/$cls.log"; then
            fail=$((fail + 1))
            failed+=("$rel/$cls (test output)")
            echo "FAIL  $rel/$cls  reported a failing test case (see out/$rel/$cls.log)"
        else
            pass=$((pass + 1))
            echo "PASS  $rel/$cls"
        fi
    done
done < <(find "$ROOT" -name '*.java' -not -path "$OUT/*" -not -path "*/.git/*" -exec dirname {} \; | sort -u)

echo
echo "Passed: $pass   Failed: $fail"
if (( fail > 0 )); then
    printf '  %s\n' "${failed[@]}"
    exit 1
fi
