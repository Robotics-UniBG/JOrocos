#include <vector>

using namespace std;

namespace JOrocos{

struct Vector3{

	double x;
	double y;
	double z;

};

struct Quaternion{

	double x;
	double y;
	double z;
	double w;

};

struct Pose{

	Vector3 position;
	Quaternion  orientation;

};

struct Path{

	double stamp;
	string frameId;
	vector<Pose> poses;

};

}
