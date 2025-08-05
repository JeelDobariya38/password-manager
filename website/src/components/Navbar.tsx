import Link from "next/link";

export default function Navbar() {
  return (
    <div className="bg-slate-800 flex justify-between items-center text-xl p-4">
      <div>
        <Link href="/">
          <h1>Passcodes</h1>
        </Link>
      </div>
      <div className="flex justify-evenly items-center gap-4">
        <Link href="/download">
          <p>Download</p>
        </Link>
        <Link href="/license">
          <p>License</p>
        </Link>
      </div>
    </div>
  );
}
