param([string]$Filter = "")

$ErrorActionPreference = "Continue"
$Root = Split-Path -Parent $PSScriptRoot
$Out = Join-Path $Root "out"
$Javac = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME "bin\javac.exe" } else { "javac" }
$Java = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME "bin\java.exe" } else { "java" }

$pass = 0
$failed = @()

if (Test-Path $Out) { Remove-Item -Recurse -Force $Out }

$dirs = Get-ChildItem -Path $Root -Recurse -Filter *.java |
    Where-Object { $_.FullName -notlike "$Out*" } |
    ForEach-Object { $_.DirectoryName } |
    Sort-Object -Unique

foreach ($dir in $dirs) {
    $rel = $dir.Substring($Root.Length + 1)
    if ($Filter -and -not $rel.StartsWith($Filter)) { continue }
    $dest = Join-Path $Out $rel
    New-Item -ItemType Directory -Force -Path $dest | Out-Null
    $sources = @(Get-ChildItem -Path $dir -Filter *.java | ForEach-Object { $_.FullName })
    & $Javac -encoding UTF-8 -d $dest $sources
    if ($LASTEXITCODE -ne 0) {
        Write-Host "COMPILE FAILED: $rel" -ForegroundColor Red
        $failed += "$rel (compile)"
        continue
    }
    foreach ($src in $sources) {
        $cls = [IO.Path]::GetFileNameWithoutExtension($src)
        $log = Join-Path $dest "$cls.log"
        Push-Location $dir
        & $Java -cp $dest $cls *> $log
        $code = $LASTEXITCODE
        Pop-Location
        $testFailed = $rel -like "*problems*" -and (Select-String -Path $log -Pattern '\b(FAIL|MISMATCH)\b' -Quiet)
        if ($code -eq 0 -and -not $testFailed) {
            $pass++
            Write-Host "PASS  $rel\$cls" -ForegroundColor Green
        } else {
            $failed += "$rel\$cls"
            Write-Host "FAIL  $rel\$cls  (see $log)" -ForegroundColor Red
        }
    }
}

Write-Host ""
Write-Host "Passed: $pass   Failed: $($failed.Count)"
if ($failed.Count -gt 0) {
    $failed | ForEach-Object { Write-Host "  $_" }
    exit 1
}
