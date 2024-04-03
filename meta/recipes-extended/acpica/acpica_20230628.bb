SUMMARY = "ACPICA tools for the development and debug of ACPI tables"
DESCRIPTION = "The ACPI Component Architecture (ACPICA) project provides an \
OS-independent reference implementation of the Advanced Configuration and \
Power Interface Specification (ACPI). ACPICA code contains those portions of \
ACPI meant to be directly integrated into the host OS as a kernel-resident \
subsystem, and a small set of tools to assist in developing and debugging \
ACPI tables."

HOMEPAGE = "https://www.intel.com/content/www/us/en/developer/topic-technology/open/acpica/overview.html"
SECTION = "console/tools"

LICENSE = "Intel | BSD-3-Clause | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://source/compiler/aslcompile.c;beginline=7;endline=150;md5=79a69059b499bccc70a484459549758f"

COMPATIBLE_HOST = "(i.86|x86_64|arm|aarch64).*-linux"

DEPENDS = "m4-native flex-native bison-native"

SRC_URI = "git://github.com/acpica/acpica;protocol=https;branch=master"
SRCREV = "f16a0b4d0f0edd7b78a332fcf507be2187fac21e"

S = "${WORKDIR}/git"

inherit update-alternatives

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "acpixtract acpidump"

EXTRA_OEMAKE = "CC='${CC}' \
                OPT_CFLAGS=-Wall \
                DESTDIR=${D} \
                PREFIX=${prefix} \
                INSTALLDIR=${bindir} \
                INSTALLFLAGS= \
                YACC=bison \
                YFLAGS='-y --file-prefix-map=${WORKDIR}=${TARGET_DBGSRC_DIR}' \
                "

do_install() {
    oe_runmake install
}

# iasl*.bb is a subset of this recipe, so RREPLACE it
PROVIDES = "iasl"
RPROVIDES:${PN} += "iasl"
RREPLACES:${PN} += "iasl"
RCONFLICTS:${PN} += "iasl"

BBCLASSEXTEND = "native nativesdk"
